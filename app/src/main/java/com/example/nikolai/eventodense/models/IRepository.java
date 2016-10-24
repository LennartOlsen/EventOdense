package com.example.nikolai.eventodense.models;

import com.example.nikolai.eventodense.models.IDataModel;
import com.example.nikolai.eventodense.models.Point.Point;

import java.util.ArrayList;

/**
 * Created by lennartolsen on 21/10/2016.
 */

public interface IRepository {
    public IDataModel get(String id);
    public ArrayList<IDataModel> get();

    /**
     * Save one
     * @param model
     * @return
     */
    public Boolean save(IDataModel model);

    /**
     * Save batch
     * @param models
     * @return
     */
    public Boolean save(ArrayList<IDataModel> models);
    public int count();
}
