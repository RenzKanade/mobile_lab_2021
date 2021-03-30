package com.example.week08a_31720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private RadioGroup groupType;
    private EditText edFileName;
    private EditText edText;
    private File tempDir,localDir,extDir,curDir;
    private Context context;
    private Button openBtn;
    private static PopupMenu chooseFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groupType = (RadioGroup) findViewById(R.id.rgType);
        edFileName = (EditText) findViewById(R.id.fileName);
        edText = (EditText) findViewById(R.id.edText);
        tempDir = getCacheDir();
        localDir = getFilesDir();
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            extDir = getExternalFilesDir(null);
        }else{
            findViewById(R.id.rbExternal).setEnabled(false);
            extDir = null;
        }
        curDir = localDir;
        groupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String choice = ((RadioButton) findViewById(groupType.getCheckedRadioButtonId())).getText().toString();

                if(choice.equalsIgnoreCase("Temporary")){
                    curDir = tempDir;
                } else if(choice.equalsIgnoreCase("Internal")){
                    curDir = localDir;
                }else{
                    curDir = extDir;
                }
            }
        });

        context = this;
        openBtn = (Button) findViewById(R.id.btnOpen);
        chooseFile = new PopupMenu(context,openBtn);
        chooseFile.getMenuInflater().inflate(R.menu.empty_menu,chooseFile.getMenu());

        chooseFile.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                edFileName.setText(item.getTitle().toString());
                edFileName.setText("");
                edText.setText("");
                return true;
            }
        });
    }

    public void OpenFile(View view){
        File[] files = null;
        if(curDir!= null){
            files = curDir.listFiles();
        }
        if(files!=null){
            int n = files.length;
            chooseFile.getMenu().clear();
            for(int i = 0;i<files.length;i++){
                chooseFile.getMenu().add(files[i].getName());
            }
            chooseFile.show();
            ReadFile();
        }else{
            Toast.makeText(context,"Problem Occurred"+"Empty Folder",Toast.LENGTH_LONG).show();
        }
    }

    private void ReadFile(){
        if(edFileName.getText().toString().length()>0){
            File file = new File(curDir,edFileName.getText().toString());
            String fileContent = "";
            try{
                InputStream inStream = new FileInputStream(file);
                if(inStream != null){
                    InputStreamReader isReader = new InputStreamReader(inStream);
                    BufferedReader bReader = new BufferedReader(isReader);
                    String accText = "";
                    StringBuilder sb = new StringBuilder();
                    while ((accText = bReader.readLine())!= null){
                        sb.append(accText).append("\n");
                    }
                    inStream.close();
                    fileContent = sb.toString();
                    edText.setText(fileContent);
                }
            }catch (FileNotFoundException e){
                Toast.makeText(context,"File not Found",Toast.LENGTH_LONG).show();
            }catch (IOException e){
                Toast.makeText(context,"IO Error detected",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void SaveFile(View view){
        String nFile = edFileName.getText().toString();
        String fContent = edText.getText().toString();
        if(nFile.length() >0 && fContent.length()>0 && curDir !=null){
            File file = new File(curDir,nFile);
            try{
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
                writer.write(fContent);
                writer.close();
                Toast.makeText(this,"Text Saved",Toast.LENGTH_LONG).show();
            }catch (FileNotFoundException e){
                Toast.makeText(this,"File not Found",Toast.LENGTH_LONG).show();
            }catch (IOException e){
                Toast.makeText(this,"IO Failure",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void DeleteFile(View view){
        if(edFileName.getText().toString().length()>0){
            boolean success = false;
            if(curDir != null & curDir == localDir){
                success = context.deleteFile(edFileName.getText().toString());
            }else{
                success = new File(curDir,edFileName.getText().toString()).delete();
            }
            if (success){
                Toast.makeText(context, " File Deleted",Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(context,"File not Deleted", Toast.LENGTH_LONG).show();
            }
            edFileName.setText("");
            edText.setText("");
        }
    }
    public void ClearText(View view){
        edText.setText("");
        edFileName.setText("");
    }
    public void ExitApp(View view){
        finishAffinity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File[] tempFiles = tempDir.listFiles();
        for(File tempFile : tempFiles){
            if(tempFile.isFile()){
                tempFile.delete();
            }
        }
    }
}