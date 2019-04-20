package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;


    private Context mContext;

    private View dialogBoxView;
    private EditText projectName;
    private EditText projectDescription;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogBoxView = inflater.inflate(R.layout.create_project_dialog, container, false);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View onClickView){
                createProjectDialog("Create project box", "Create project box",
                        "cancelMethod1", "createMethod1");
            }

        });

        listItems = new ArrayList<>();
        onAttach(this.getActivity());
        updateProjectsList();
        adapter = new MyAdapter(listItems, mContext);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    /**
     * Dialog box which will create project
     * @param title
     * @param message
     * @param cancelMethod
     * @param createMethod
     */
    public void createProjectDialog(String title, String message,
                                   final String cancelMethod, final String createMethod){
        final android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(mContext);

        dialogBuilder.setTitle(title);

        if(dialogBoxView.getParent() != null){
            ((ViewGroup) dialogBoxView.getParent()).removeView(dialogBoxView);
        }

        dialogBuilder.setView(dialogBoxView);

        dialogBuilder.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "Creation canceled");
                        if (cancelMethod.equals("cancelMethod1"))
                            cancelMethod1();
                    }
                }
        );

        dialogBuilder.setPositiveButton(
                "Create",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "Project created");
                        if (createMethod.equals("createMethod1"))
                            createMethod1();

                    }
                }
        );

        dialogBuilder.show();
    }

    private void cancelMethod1(){
        Log.d(TAG, "Cancel Method called");
        toastMassage("Cancel Method1");
    }


    private void createMethod1(){
        projectName = (EditText) dialogBoxView.findViewById(R.id.projectDialog_editText_name);
        projectDescription = (EditText) dialogBoxView.findViewById(R.id.projectDialog_editText_description);
        boolean isCreated = LoginActivity.database.insertData_Project(projectName.getText().toString(), projectDescription.getText().toString());
        if(isCreated) {
            toastMassage("Project created");
            updateProjectsList();
            adapter.notifyDataSetChanged();
        }
        else
            toastMassage("Error. Project not created");


    }

    private void toastMassage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    private void updateProjectsList(){
        clearList(listItems);

        Cursor result = LoginActivity.database.getAllData(Database.ProjectEntity.TABLE_NAME);
        if(result.getCount() == 0){
            //show err mess

        }
        while (result.moveToNext()){
            ListItem listItem = new ListItem(result.getString(0),
                    result.getString(1));
            listItems.add(listItem);
        }

    }

    private void clearList(List list){
        final int size = list.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                list.remove(0);
            }
        }
    }


}
