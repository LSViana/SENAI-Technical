package sstorage.mobile.senai.com.sstorage.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.model.Environment;
import sstorage.mobile.senai.com.sstorage.view.holder.EnvironmentViewHolder;

public class EnvironmentAdapter extends RecyclerView.Adapter {

    private List<Environment> environmentList;
    private Context context;

    public EnvironmentAdapter(List<Environment> environmentList, Context context) {
        this.environmentList = environmentList;
        this.context = context;
    }

    // Method called when creating ViewHolders (items)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvitem_simpleitem, parent, false);
        EnvironmentViewHolder evh = new EnvironmentViewHolder(view, this);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EnvironmentViewHolder evh = (EnvironmentViewHolder) holder;
        Environment environment = environmentList.get(position);
        evh.fill(environment);
    }

    @Override
    public int getItemCount() {
        return environmentList.size();
    }

    public List<Environment> getEnvironmentList() {
        return environmentList;
    }

    public void setEnvironmentList(List<Environment> environmentList) {
        this.environmentList = environmentList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
