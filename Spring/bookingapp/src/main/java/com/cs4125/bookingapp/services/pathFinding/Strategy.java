package com.cs4125.bookingapp.services.pathFinding;

import com.cs4125.bookingapp.model.entities.Connection;

import java.util.List;

public interface Strategy {

    String findPath(String startNode, String endNode, List<Connection> connections);

}