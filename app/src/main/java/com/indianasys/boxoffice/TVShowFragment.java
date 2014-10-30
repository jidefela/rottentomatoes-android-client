package com.indianasys.boxoffice;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fima.cardsui.objects.CardStack;
import com.fima.cardsui.views.CardUI;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TVShowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TVShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class TVShowFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CardUI mCardView;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TVShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TVShowFragment newInstance(String param1, String param2) {
        TVShowFragment fragment = new TVShowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public TVShowFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tvshow, container, false);

        mCardView = (CardUI) view.findViewById(R.id.cardsview);
        //mCardView.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_repeater));


        CardStack stack2 = new CardStack();
        stack2.setTitle("                                                                   ");
        mCardView.addStack(stack2);

        /*ImageCard mycard = new ImageCard("How i met your mother", "",R.drawable.movie_template);
        ImageCard mycard1 = new ImageCard("Game of Thrones", "",R.drawable.movie_template);
        ImageCard mycard2 = new ImageCard("Breaking Bad", "",R.drawable.movie_template);
        ImageCard mycard3 = new ImageCard("Uoija", "",R.drawable.movie_template);
        ImageCard mycard4 = new ImageCard("Two and a half men", "",R.drawable.movie_template);


        mCardView.addCard(mycard);
        mCardView.addCard(mycard1);
        mCardView.addCard(mycard2);
        mCardView.addCard(mycard3);
        mCardView.addCard(mycard4);
        mCardView.refresh();*/


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }






    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
