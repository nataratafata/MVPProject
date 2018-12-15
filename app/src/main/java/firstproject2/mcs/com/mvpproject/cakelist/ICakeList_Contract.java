package firstproject2.mcs.com.mvpproject.cakelist;

import java.util.List;

import firstproject2.mcs.com.mvpproject.data.CakesModel;


public interface ICakeList_Contract {

    interface IView_CakeList{
        // methods which will be called by the presenter by using view instance
        void _displayProgressDialog();
        void _cakesList(List<CakesModel> cakesModels);
        void _dismissProgressDialog();
    }


    interface IPresenter_CakeList{
     // methods to be called in the view by using the presenter instance

        void onBind(IView_CakeList iView_cakeList);
        void getCakesFromAPI();
        void unBind();
    }
}
