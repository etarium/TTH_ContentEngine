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

	public List<Location> getAllCells() {
		List<Location> locationList = new ArrayList();
		MongoCollection<Document> cellCollection = database.getCollection("Cells");

		Iterable<Document> cellDocuments = cellCollection.find();
		cellDocuments.forEach(cell -> {
			try {
				Cell tempCell = mapper.readValue(cell.toJson(), Cell.class);
				locationList.add(tempCell.getLocation());
			} catch (IOException e) {
			}
		});
		return locationList;
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
		BasicDBObject criteria = new BasicDBObject();
		criteria.append("location.x", loc.getX());
		criteria.append("location.y", loc.getY());
		criteria.append("z", loc.getZ());
		MongoCollection<Document> cellCollection = database.getCollection("Cells");
		Iterable<Document> cellDocuments = cellCollection.find(eq(criteria));
		cellDocuments.forEach(cell -> {
			try {
				Cell tempCell = mapper.readValue(cell.toJson(), Cell.class);
				cellObject = tempCell;
			} catch (IOException e) {
				System.out.println("Reading Cells into Cell Object failed.");
			}
		});
		return cellObject;
	}
}
