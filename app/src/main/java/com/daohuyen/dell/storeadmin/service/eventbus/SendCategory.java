package com.daohuyen.dell.storeadmin.service.eventbus;

import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;

public class SendCategory {
    private CategoryViewModel categoryViewModel;

    public SendCategory() {
    }

    public SendCategory(CategoryViewModel categoryViewModel) {
        this.categoryViewModel = categoryViewModel;
    }

    public CategoryViewModel getCategoryViewModel() {
        return categoryViewModel;
    }

    public void setCategoryViewModel(CategoryViewModel categoryViewModel) {
        this.categoryViewModel = categoryViewModel;
    }
}
