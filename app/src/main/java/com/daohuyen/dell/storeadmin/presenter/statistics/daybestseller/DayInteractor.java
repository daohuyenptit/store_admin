package com.daohuyen.dell.storeadmin.presenter.statistics.daybestseller;


import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.OnsuccessStatistics;

public interface DayInteractor extends BaseInteractor {
    void getAllBest(String sd, OnsuccessStatistics onsuccessStatistics);

}
