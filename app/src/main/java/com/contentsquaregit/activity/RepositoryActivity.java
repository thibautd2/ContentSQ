package com.contentsquaregit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.contentsquaregit.R;
import com.contentsquaregit.Tools.RoundedPicasso;
import com.contentsquaregit.model.Repository;
import com.squareup.picasso.Picasso;

/**
 * Created by thiba_000 on 04/11/2016.
 */

public class RepositoryActivity extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        repository = (Repository) getIntent().getSerializableExtra("repository");
        bindView();
    }

    public void bindView()
    {
        TextView title = (TextView) findViewById(R.id.repository_title);
        TextView owner = (TextView) findViewById(R.id.repository_owner);
        TextView stars = (TextView) findViewById(R.id.repository_star);
        TextView fork = (TextView) findViewById(R.id.repository_forks);
        TextView details = (TextView) findViewById(R.id.repository_detail);
        TextView watch = (TextView) findViewById(R.id.repository_nbviews);
        ImageView avatar = (ImageView) findViewById(R.id.repository_avatar);
        TextView update = (TextView) findViewById(R.id.repository_update);
        TextView html_link = (TextView) findViewById(R.id.repository_url);

        title.setText(repository.getName());
        owner.setText(repository.getOwner().getLogin());
        stars.setText(repository.getWatchers_count());
        fork.setText(repository.getForks_count());
        details.setText(repository.getDescription());
        watch.setText(repository.getSubscribers_count());
        update.setText(repository.getUpdated_at().substring(0,10));
        html_link.setText(repository.getHtml_url());

        Picasso.with(this).load(repository.getOwner().getAvatar_url()).fit().centerCrop().transform(new RoundedPicasso()).into(avatar);
    }


}
