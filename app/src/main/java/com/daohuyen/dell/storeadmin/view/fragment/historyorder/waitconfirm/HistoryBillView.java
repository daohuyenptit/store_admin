package com.daohuyen.dell.storeadmin.view.fragment.historyorder.waitconfirm;
import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.view.BillView;

public interface HistoryBillView {
    void showLoadMoreProgress();
    void hideLoadMoreProgress();
    void enableLoadMore(boolean enable);
    void enableRefreshing(boolean enable);
    void showRefreshingProgress();
    void hideRefreshingProgress();
    void addBillPreviews(PageList<BillView> billViewPageList);
    void refreshBillPreview(PageList<BillView> billViewPageList);
}
