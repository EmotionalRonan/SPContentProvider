package tk.iovr.spcontentprovider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pl.sphelper.SPHelper;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = findViewById(R.id.add);
        Button query = findViewById(R.id.query);
        EditText editText = findViewById(R.id.ip_editext);
        TextView textView = findViewById(R.id.textView);


        SPHelper.init(getApplication());


        add.setOnClickListener(view ->{
            saveIntAndBool(view);
            saveStringAndFloat(view);
            saveStringSet(view);


        });

        query.setOnClickListener(this::getValue);



    }

    public void saveIntAndBool(View view){
        long start=System.currentTimeMillis();
        SPHelper.save("a",100);
        SPHelper.save("z",true);
        long end=System.currentTimeMillis();
        Toast.makeText(this,"takes "+(end-start)+"millis",Toast.LENGTH_SHORT).show();
    }
    public void saveStringAndFloat(View view){
        long start=System.currentTimeMillis();
        SPHelper.save("b","aaa");
        SPHelper.save("x",1.001f);
        long end=System.currentTimeMillis();
        Toast.makeText(this,"takes "+(end-start)+"millis",Toast.LENGTH_SHORT).show();;
    }
    public void saveStringSet(View view){
        Set<String> set=new HashSet<>();
        set.add("aaa,bbb");
        set.add("ccc");
        long start=System.currentTimeMillis();
        SPHelper.save("c",set);
        long end=System.currentTimeMillis();
        Toast.makeText(this,"takes "+(end-start)+"millis",Toast.LENGTH_SHORT).show();;
    }
    public void clean(View view){
        long start=System.currentTimeMillis();
        SPHelper.clear();
        long end=System.currentTimeMillis();
        Toast.makeText(this,"takes "+(end-start)+"millis",Toast.LENGTH_SHORT).show();;
    }
    public void getValue(View view){
        long start=System.currentTimeMillis();

        Map<String,?> map=SPHelper. getAll();

//        String result1 = "a="+SPHelper.getInt("a",0);
//        String result2 = "b="+SPHelper.getString("b","");
//
//        Set<String> set = SPHelper.getStringSet("c",null);

        long end=System.currentTimeMillis();
        Toast.makeText(this,"takes "+(end-start)+"millis",Toast.LENGTH_SHORT).show();;
        String result="";
        for (Map.Entry<String, ?> entry:map.entrySet()){
            String k = entry.getKey();
            Object v = entry.getValue();
            if (v instanceof Set){
                String result3="";
                for (String string:(Set<String>)v){
                    result3+="\""+string+"\"";
                    result3+="    ";
                }
                v=result3;
            }
            result+=k+"="+v+"\n";
        }


        TextView value= (TextView) findViewById(R.id.textView);
        value.setText(result);
    }


}