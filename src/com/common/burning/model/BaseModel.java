package com.common.burning.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.util.Base64;

public class BaseModel implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    public String serializeToString() {
        String encoded = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            encoded = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encoded;
    }

    public static BaseModel deSerializeFromString(String string) {
        if (string == null) {
            return null;
        }
        byte[] bytes = Base64.decode(string.getBytes(), Base64.DEFAULT);
        BaseModel data = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream( new ByteArrayInputStream(bytes) );
            data = (BaseModel)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return data;
    }
}

