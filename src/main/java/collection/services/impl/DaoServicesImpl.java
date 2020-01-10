package collection.services.impl;

import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import collection.model.PhotosRepo;
import collection.services.DaoServices;

public class DaoServicesImpl implements DaoServices {
	
	private AmazonDynamoDB client;
	private DynamoDBMapper mapper;
	

	public DaoServicesImpl() {
		super();
		client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_3).build();
		mapper = new DynamoDBMapper(client);
	}

	
	@Override
	public void setTel(String id, boolean setTel) {
		PhotosRepo toEdit = mapper.load(PhotosRepo.class, id);
		toEdit.setTelephone(setTel);
		mapper.save(toEdit);
	}

	@Override
	public void insert(PhotosRepo toCreate) {
		mapper.save(toCreate);
	}
	
	@Override
	public void setServeur(String id, boolean setter) {
		PhotosRepo toEdit = mapper.load(PhotosRepo.class, id);
		toEdit.setServeur(setter);
		mapper.save(toEdit);
	}
	
	@Override
	public void setHubic(String id, boolean setter) {
		PhotosRepo toEdit = mapper.load(PhotosRepo.class, id);
		toEdit.setHubic(setter);
		mapper.save(toEdit);
	}
	
	@Override
	public void setGlacier(String id, boolean setter) {
		PhotosRepo toEdit = mapper.load(PhotosRepo.class, id);
		toEdit.setGlacier(setter);
		mapper.save(toEdit);
	}
	
	@Override
	public List<PhotosRepo> getAllRepos(){
		return mapper.scan(PhotosRepo.class, new DynamoDBScanExpression());
	}

}
