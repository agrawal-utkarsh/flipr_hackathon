package flipr;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class DeviceService {
	
	public List<Device> getAll() {
		MongoClientURI uri = new MongoClientURI("mongodb+srv://backendconcoxdeveloper:V3jUV7QXqEoAtnhy@cluster0-zhjde.mongodb.net");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase db = mongoClient.getDatabase("__CONCOX__");
		MongoCollection<Document> collection=db.getCollection("devices");
		System.out.println("COUNT : " + collection.count());
		FindIterable<Document> docs = collection.find();
		List<Device> l = new ArrayList<Device>();
		for (Document doc : docs) 
		{
			l.add(new Device(doc.getString("id"),doc.getString("imei"),doc.getString("client"),doc.getString("tel")));
		}		
		return l;
	}

	public List<Location> getLocations(String deviceId, Integer page) {
		MongoClientURI uri = new MongoClientURI("mongodb+srv://backendconcoxdeveloper:V3jUV7QXqEoAtnhy@cluster0-zhjde.mongodb.net");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase db = mongoClient.getDatabase("__CONCOX__");
		MongoCollection<Document> collection=db.getCollection("status");
		System.out.println("COUNT : " + collection.count());
		FindIterable<Document> docs = collection.find();
		List<Location> l = new ArrayList<>();
		if(page==null)
		{
			page=1;
		}
		int i=0;
		for (Document doc : docs) 
		{
			if(i<page*10)
			{
				if(doc.getString("imei").equals(deviceId))
				{
					System.out.println(doc);
					List<Double>ll=new ArrayList<>();
					ll= (List<Double>) doc.get("loc");
					if(ll != null)
					{
						i++;
						l.add(new Location(ll.get(0),ll.get(1) ));					
					}
				}
			}
			else break;
		}
		List<Location> finalList = new ArrayList<>();
		for(int j=(page-1)*10;j<page*10;j++)
		{
			finalList.add(l.get(j));
		}
		return finalList;
	}

}

