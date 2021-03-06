package tth_engine;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import environment.Cell;
import environment.Location;

public class DBConnector {
	MongoDatabase database;
	ObjectMapper mapper = new ObjectMapper();
	Cell cellObject = new Cell();

	public DBConnector() {
		ConfigReader config = new ConfigReader();

		String user = config.getProperty("user");
		String pass = config.getProperty("password");
		String dbName = config.getProperty("database");
		MongoClientURI uri = new MongoClientURI(
				"mongodb+srv://"+user+":"+pass+"@thetwistinghaunt-shard-00-00-hh6b2.mongodb.net/admin?ssl=true&replicaSet=TheTwistingHaunt-shard-0&readPreference=secondary");

		MongoClient mongoClient = new MongoClient(uri);
		database = mongoClient.getDatabase(dbName);

	}

	public List<Cell> getAllCells() {
		List<Cell> activeCells = new ArrayList();
		MongoCollection<Document> cellCollection = database.getCollection("Cells");
		Iterable<Document> cellDocuments = cellCollection.find();
		cellDocuments.forEach(cell -> {
			try {
				Cell tempCell = mapper.readValue(cell.toJson(), Cell.class);
				activeCells.add(tempCell);
			} catch (IOException e) {
				System.out.println("Reading Cells into Cell Object failed.");
				System.out.println(e);
			}
		});
		return activeCells;
	}

	public List<Cell> getCellsByInstance(String instance) {
		List<Cell> activeCells = new ArrayList();
		MongoCollection<Document> cellCollection = database.getCollection("Cells");
		Iterable<Document> cellDocuments = cellCollection.find(eq("instance.name", instance));
		System.out.println(cellDocuments);
		cellDocuments.forEach(cell -> {
			try {
				Cell tempCell = mapper.readValue(cell.toJson(), Cell.class);
				activeCells.add(tempCell);
			} catch (IOException e) {
				System.out.println("Reading Cells into Cell Object failed.");
				System.out.println(e);
			}
		});
		return activeCells;
	}
	
	public Cell getCellByLocation(Location loc) {
		
		Cell activeCell = new Cell();
		
		BasicDBObject criteria = new BasicDBObject();
		criteria.append("x", loc.getX());
		criteria.append("y", loc.getY());
		criteria.append("z", loc.getZ());

		MongoCollection<Document> cellCollection = database.getCollection("Cells");
		Document cellDocument = cellCollection.find(new BasicDBObject("location", criteria)).first();
		
		try {
			if(cellDocument != null) {
			activeCell = mapper.readValue(cellDocument.toJson(), Cell.class);
			}
		} catch (IOException e) {
			System.out.println("Reading Cells into Cell Object failed.");
		}
		return activeCell;
	}

	public void writeCell(Cell cell) {
		MongoCollection<Document> cellCollection = database.getCollection("Cells");
		try {
			String cellString = mapper.writeValueAsString(cell);
			String originalCellString = mapper.writeValueAsString(CellEditor.cell.getLocation());
			Document cellDocument = Document.parse(cellString);
			Document update = new Document();
			update.append("$set",  cellDocument);
			if(CellEditor.cell.getLocation() != null) {
				System.out.println(CellEditor.cell);
				Document query = new Document();
				query.append("location", originalCellString);
				
			cellCollection.updateOne(query, update);
			} else {
				System.out.println(CellEditor.cell);
				System.out.println("hit else case");
				cellCollection.insertOne(cellDocument);
			}
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
