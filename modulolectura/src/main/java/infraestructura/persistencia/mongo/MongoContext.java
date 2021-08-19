package infraestructura.persistencia.mongo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import infraestructura.persistencia.mongo.model.PersonaModel;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class MongoContext {

    @Value("${mongodb.database}")
    private String databaseName;

    @Inject
    private MongoClient mongoClient;

    @Singleton
    public MongoCollection<PersonaModel> getMongoCollectionPersona(){
        return mongoClient.getDatabase(databaseName).getCollection("personas", PersonaModel.class);
    }
}
