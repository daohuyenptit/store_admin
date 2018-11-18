package com.daohuyen.dell.storeadmin.presenter.statistics.monthbestseller;


import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.OnsuccessStatistics;

public interface MonthInteractor extends BaseInteractor {
    void getAllBest(String sd,OnsuccessStatistics onsuccessStatistics);

}
