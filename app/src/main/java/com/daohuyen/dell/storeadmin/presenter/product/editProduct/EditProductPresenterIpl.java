package com.daohuyen.dell.storeadmin.presenter.product.editProduct;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.daohuyen.dell.storeadmin.model.body.ProductBody;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.service.eventbus.SendProduct;
import com.daohuyen.dell.storeadmin.view.product.editProduct.EditProductView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

public class EditProductPresenterIpl implements EditProductPresenter {
    private Context context;
    private EditProductView addProductView;
    private EditProductInteractor editProductInteractor;

    public EditProductPresenterIpl(Context context, EditProductView addProductView) {
        this.context = context;
        this.addProductView = addProductView;
        this.editProductInteractor =new EditProductInteractorIpl(context);
    }

    @Override
    public void onViewDestroy() {
        editProductInteractor.onViewDestroy();

    }

    @Override
    public void editProduct(String productID, Uri filePath,ProductBody productViewModel) {
        if (filePath != null) {
            addProductView.showProgress();
            FirebaseApp.initializeApp(context);
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            //  FirebaseOptions opts = FirebaseApp.getInstance().getOptions();
            StorageReference sr = firebaseStorage.getReference();
            StorageReference storageReference = sr.getStorage().getReference().child("product/" + UUID.randomUUID().toString());
            storageReference.putFile(filePath).addOnSuccessListener(taskSnapshot -> {
                productViewModel.setLogoUrl(taskSnapshot.getDownloadUrl().toString());
                Log.i("firebaswURI", "onSuccess: " + taskSnapshot.getDownloadUrl().toString());
                editSuccess(productID, productViewModel);
            }).addOnFailureListener(e -> {
                Log.i("firebaswURI", "onSuccess:" + e.getCause().toString());
                Toast.makeText(context, "Upload Image Fail!", Toast.LENGTH_SHORT).show();
                return;
            });
        }else{
            Toast.makeText(context, "Bạn chưa chọn ảnh!", Toast.LENGTH_SHORT).show();
        }


    }
    public void editSuccess(String productID,ProductBody productViewModel){
        editProductInteractor.editProduct(productID, productViewModel, new OnGetEditSuccess() {
            @Override
            public void onSuccess(ProductViewModel productViewModel1) {
                addProductView.hideProgress();
                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                SendProduct sendProduct=new SendProduct();
                ProductViewModel productViewModel2=new ProductViewModel();
                sendProduct.setProductViewModel(productViewModel1);
                EventBus.getDefault().post(sendProduct);
                addProductView.backToHomeScreen();
            }

            @Override
            public void onError(String message) {
                addProductView.hideProgress();
                Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
