package tth_engine;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pojos.environment.Cell;
import pojos.environment.Location;

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
				"mongodb+srv://"+user+":"+pass+"@thetwistinghaunt-shard-00-01-hh6b2.mongodb.net/admin");

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
			}
		});
		return activeCells;
	}

	public List<Cell> getCellsByInstance(String instance) {
		List<Cell> activeCells = new ArrayList();
		MongoCollection<Document> cellCollection = database.getCollection("Cells");
		Iterable<Document> cellDocuments = cellCollection.find(eq("instance.name", instance));
		cellDocuments.forEach(cell -> {
			try {
				Cell tempCell = mapper.readValue(cell.toJson(), Cell.class);
				activeCells.add(tempCell);
			} catch (IOException e) {
				System.out.println("Reading Cells into Cell Object failed.");
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
}
