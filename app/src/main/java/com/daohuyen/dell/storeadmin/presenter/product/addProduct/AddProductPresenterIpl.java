package com.daohuyen.dell.storeadmin.presenter.product.addProduct;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.daohuyen.dell.storeadmin.model.body.ProductBody;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.view.product.addProduct.AddProductView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class AddProductPresenterIpl implements AddProductPresenter {
    private Context context;
    private AddProductView addProductView;
    private AddProductInteractor addProductInteractor;

    public AddProductPresenterIpl(Context context, AddProductView addProductView) {
        this.context = context;
        this.addProductView = addProductView;
        this.addProductInteractor=new AddProductInteractorIpl(context);
    }

    @Override
    public void onViewDestroy() {
        addProductInteractor.onViewDestroy();

    }

    @Override
    public void addProduct(String categoryID, Uri filePart, ProductBody productViewModel) {

        if (filePart != null) {
            addProductView.showProgress();
            FirebaseApp.initializeApp(context);
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            //  FirebaseOptions opts = FirebaseApp.getInstance().getOptions();
            StorageReference sr = firebaseStorage.getReference();
            StorageReference storageReference = sr.getStorage().getReference().child("product/" + UUID.randomUUID().toString());
            storageReference.putFile(filePart).addOnSuccessListener(taskSnapshot -> {
                productViewModel.setLogoUrl(taskSnapshot.getDownloadUrl().toString());
                Log.i("firebaswURI", "onSuccess: " + taskSnapshot.getDownloadUrl().toString());
                addSuccessProduct(categoryID, productViewModel);
            }).addOnFailureListener(e -> {
                Log.i("firebaswURI", "onSuccess:" + e.getCause().toString());
                Toast.makeText(context, "Upload Image Fail!", Toast.LENGTH_SHORT).show();
                return;
            });
        }else{
            Toast.makeText(context, "Bạn chưa chọn ảnh!", Toast.LENGTH_SHORT).show();
        }


    }
    private void addSuccessProduct(String categoryID, ProductBody productBody){
        addProductInteractor.addProduct(categoryID, productBody, new OnGetAddSuccess() {
            @Override
            public void onSuccess() {
                addProductView.hideProgress();
                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                addProductView.hideProgress();
                Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
