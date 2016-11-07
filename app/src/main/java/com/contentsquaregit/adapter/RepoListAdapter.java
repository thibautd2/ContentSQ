package com.contentsquaregit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.contentsquaregit.R;
import com.contentsquaregit.Tools.InternetConnection;
import com.contentsquaregit.model.Repository;
import com.contentsquaregit.model.User;
import com.contentsquaregit.service.GetRepositoryDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by thiba_000 on 04/11/2016.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private ArrayList<Repository> repositories;
    private Context context;

    public RepoListAdapter(ArrayList<Repository> repositories, Context context) {
        this.repositories = repositories;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titre, username, description;
        private ImageView user_avatar;
        private RelativeLayout layout;

        private ViewHolder(View v) {
            super(v);
            titre = (TextView) v.findViewById(R.id.item_repo_title);
            username = (TextView) v.findViewById(R.id.item_repo_username);
            user_avatar = (ImageView) v.findViewById(R.id.item_repo_user_avatar);
            layout = (RelativeLayout) v.findViewById(R.id.item_repo_layout);
            description = (TextView) v.findViewById(R.id.item_repo_description);
        }
    }


    @Override
    public RepoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo_list, parent, false);
        return (new ViewHolder(v));
    }

    @Override
    public void onBindViewHolder(RepoListAdapter.ViewHolder vh, int position) {
        final Repository repo = repositories.get(position);
        User owner = repo.getOwner();
        vh.titre.setText(repo.getName());
        vh.username.setText(context.getString(R.string.repo_list_owner)+owner.getLogin());
        vh.description.setText(repo.getDescription());
        Picasso.with(context).load(owner.getAvatar_url()).fit().centerCrop().placeholder(R.drawable.avatar).into(vh.user_avatar);
        vh.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(InternetConnection.testingNetwork(context))
                new GetRepositoryDetails(context, repo.getFull_name()).execute();
            }
        });

    }


    @Override
    public int getItemCount() {
        return repositories.size();
    }
}
