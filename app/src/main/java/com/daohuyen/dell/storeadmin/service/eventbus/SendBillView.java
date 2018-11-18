package com.daohuyen.dell.storeadmin.service.eventbus;

import com.daohuyen.dell.storeadmin.model.view.BillView;

import java.util.List;

public class SendBillView {
    private List<BillView> list;

    public SendBillView() {
    }

    public SendBillView(List<BillView> list) {
        this.list = list;
    }

    public List<BillView> getList() {
        return list;
    }

    public void setList(List<BillView> list) {
        this.list = list;
    }
}
