package com.example.annotationprocessor;

/**
 * Created by Lenovo on 27/07/2016.
 */
public @interface checkFlag {
    String[] values()default "";
}
