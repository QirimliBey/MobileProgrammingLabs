package com.example.project;

public class ListItem {
    private String project_name;
    private String project_description;

    public ListItem(String project_name, String project_description) {
        this.project_name = project_name;
        this.project_description = project_description;
    }

    public String getProject_name() {
        return project_name;
    }

    public String getProject_description() {
        return project_description;
    }
}
