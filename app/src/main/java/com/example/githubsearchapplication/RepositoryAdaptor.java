package com.example.githubsearchapplication;

import android.content.Context;
import android.media.session.MediaController;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RepositoryAdaptor extends RecyclerView.Adapter<RepoViewHolder>{
    Context mContext;
    Repository[] repoarray;

    public RepositoryAdaptor(Context context,Repository[] repoarray) {
        mContext=context;
        this.repoarray=repoarray;
    }




    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//will hold the view ;would be called once;inflation
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.itemrepo,parent,false);
        RepoViewHolder viewHolder=new RepoViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {//would be called multiple times
        Repository repo=repoarray[position];
        holder.reponame.setText(repo.name);
        holder.language.setText(repo.language);
        holder.description.setText(repo.description);
    }

    @Override
    public int getItemCount() {
        return repoarray.length;
    }
}
class RepoViewHolder extends RecyclerView.ViewHolder {//will hold data of view holder
    TextView reponame,language,description;
    public RepoViewHolder(@NonNull View itemView) {
        super(itemView);
        reponame=(TextView)itemView.findViewById(R.id.namerepo);
        language=(TextView)itemView.findViewById(R.id.langrepo);
        description=(TextView)itemView.findViewById(R.id.desc);
    }


}