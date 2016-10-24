package com.example.nikolai.eventodense.datastore.http;

import com.example.nikolai.eventodense.models.IDataModel;
import com.example.nikolai.eventodense.models.IRepository;

import java.util.ArrayList;

/**
 * Created by lennartolsen on 24/10/2016.
 */

public interface IHttpRepository extends IRepository {
    String getEndpoint();

    @Override
    IDataModel get(String id);

    @Override
    ArrayList<IDataModel> get();

    @Override
    Boolean save(IDataModel p);
}
