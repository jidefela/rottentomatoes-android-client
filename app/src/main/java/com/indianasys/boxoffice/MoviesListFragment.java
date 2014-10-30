package com.indianasys.boxoffice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fima.cardsui.views.CardUI;
import com.indianasys.boxoffice.model.BoxOfficeMovie;
import com.indianasys.boxoffice.model.BoxOfficeMoviesAdapter;
import com.indianasys.boxoffice.task.RottenTomatoesClient;
import com.indianasys.boxoffice.views.BoxOfficeDetailActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link MoviesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MoviesListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CardUI mCardView;
    private ProgressBar mProgressBar;
    SwipeRefreshLayout mSwipeView;
    private Context context;
    private View mylayout;
    LinearLayout progressView;
    private int mProgressStatus = 0;
    private ImageView imageView;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesListFragment newInstance(String param1, String param2) {
        MoviesListFragment fragment = new MoviesListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public MoviesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private BoxOfficeMoviesAdapter adapterMovies;
    private RottenTomatoesClient client;
    public static final String MOVIE_DETAIL_KEY = "movie";
    private  ListView movieList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);

        context = getActivity().getApplicationContext();
        progressView = (LinearLayout) view.findViewById(R.id.progressView);

        /*progressView = (LinearLayout) view.findViewById(R.id.progressView);
        mylayout = view.findViewById(R.id.error_Layout);
        mCardView = (CardUI) view.findViewById(R.id.cardsview);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mSwipeView = (SwipeRefreshLayout)getActivity().findViewById(R.id.swipeView);
        mSwipeView.setEnabled(false);
        Button refresh = (Button) mylayout.findViewById(R.id.refresh_button);
         imageView = (ImageView) mCardView.findViewById(R.id.imageView1);*/

        ArrayList<BoxOfficeMovie> aMovies = new ArrayList<BoxOfficeMovie>();
        adapterMovies = new BoxOfficeMoviesAdapter(context, aMovies);

        movieList = (ListView) view.findViewById(R.id.movieList);
        movieList.setAdapter(adapterMovies);
        // Fetch the data remotely
        fetchBoxOfficeMovies();
        setupMovieSelectedListener();

        return view;
    }




    public void fetchBoxOfficeMovies() {
        client = new RottenTomatoesClient();
        if(isConnectedToInternet()) {

            progressView.setVisibility(ViewGroup.VISIBLE);
            client.getBoxOfficeMovies(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int code, JSONObject body) {
                    JSONArray items = null;
                    try {
                        // Get the movies json array
                        items = body.getJSONArray("movies");
                        // Parse json array into array of model objects
                        ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJson(items);
                        // Load model objects into the adapter which displays them
                        progressView.setVisibility(ViewGroup.GONE);
                        adapterMovies.addAll(movies);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable throwable, JSONObject jsonObject) {
                    super.onFailure(throwable, jsonObject);
                    makeToast("Sorry, Couldn't Reach the Server");
                }
            });
        }
        else{
            makeToast("No Internet Connection");
        }
    }

    public void setupMovieSelectedListener() {
        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position, long rowId) {
                Intent i = new Intent(context, BoxOfficeDetailActivity.class);
                i.putExtra(MOVIE_DETAIL_KEY, adapterMovies.getItem(position));
                startActivity(i);
            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;
    }




    private void makeToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }




}
