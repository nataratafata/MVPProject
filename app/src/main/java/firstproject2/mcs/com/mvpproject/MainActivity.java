package firstproject2.mcs.com.mvpproject;

//import android.support.v7.app.AlertController;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.util.List;

import firstproject2.mcs.com.mvpproject.cakelist.CakesList_PresenterImpl;
import firstproject2.mcs.com.mvpproject.cakelist.ICakeList_Contract;
import firstproject2.mcs.com.mvpproject.data.CakesModel;
import firstproject2.mcs.com.mvpproject.data.dataAdapter;
import firstproject2.mcs.com.mvpproject.network.ConnectionService;
import firstproject2.mcs.com.mvpproject.network.ICakes_Interacter;

public class MainActivity extends AppCompatActivity implements ICakeList_Contract.IView_CakeList{
    private CakesList_PresenterImpl cakesList_presenter;
    private ICakes_Interacter iCakes_interacter;

    private RecyclerView recyclerview;
    private dataAdapter adapter;
    private ConnectionService service;
    public static File cacheDir;
    @Override
    public void _displayProgressDialog(){

    }

    @Override
    public void _cakesList(List<CakesModel> cakeModels){

            //recyclerview(CakeModel)
        recyclerview =  (RecyclerView) findViewById(R.id.recycler_view);
        recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);

        dataAdapter adapter = new dataAdapter(cakeModels);
        recyclerview.setAdapter(adapter);
        //cakesList_presenter = new CakesList_PresenterImpl(cakesModels);
        //dataAdapter adapter = new dataAdapter();
       // ConnectionService service = new ConnectionService();

        //recyclerview.setAdapter(adapter);
    }

    @Override
    public void _dismissProgressDialog() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _initializePresenterandCallApi();

        cacheDir = getCacheDir();
    }

    public void _initializePresenterandCallApi(){
        iCakes_interacter= new ConnectionService();
        cakesList_presenter = new CakesList_PresenterImpl(iCakes_interacter);
        cakesList_presenter.onBind(this);
        cakesList_presenter.getCakesFromAPI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cakesList_presenter.unBind();
    }
}
