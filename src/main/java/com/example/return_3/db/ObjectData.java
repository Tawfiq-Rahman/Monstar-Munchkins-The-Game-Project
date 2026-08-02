package com.example.return_3.db;

// Define a simple class to hold object data
public class ObjectData {
    private int userId;
    private int objectType;
    private int itemCode;
    private int col;
    private int row;
    private int mapNum;

    public ObjectData(int userId, int objectType, int itemCode, int col, int row, int mapNum) {
        this.userId = userId;
        this.objectType = objectType;
        this.itemCode = itemCode;
        this.col = col;
        this.row = row;
        this.mapNum = mapNum;
    }

    public int getUserId() {
        return userId;
    }

    public int getObjectType() {
        return objectType;
    }

    public int getItemCode() {
        return itemCode;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getMapNum() {
        return mapNum;
    }
}
