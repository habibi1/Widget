package com.tugasmobile.nowplaying;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NowPlayingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingFragment extends Fragment {
    private static final String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=" +  BuildConfig.TMDB_API_KEY+ "&language=en-US";
    private RecyclerView recyclerView;
    private List<ItemMovieModel> movieList;
    private MovieAdapter movieAdapter;

    private OnFragmentInteractionListener mListener;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        recyclerView = view.findViewById(R.id.rv_list_movie);
        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(getActivity(), movieList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        fetchMovieItems();

        return view;
    }

    private void fetchMovieItems() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray aray = response.getJSONArray("results");

                            for (int i=0; i<aray.length(); i++){
                                JSONObject object = aray.getJSONObject(i);

                                ItemMovieModel model = new ItemMovieModel();

                                model.setJudul(object.getString("title"));
                                model.setDeskripsi(object.getString("overview"));
                                model.setImageMovie(object.getString("poster_path"));
                                model.setTanggalRilis(object.getString("release_date"));
                                model.setPopularitas(object.getString("popularity"));
                                model.setRating(object.getString("vote_average"));
                                movieList.add(model);
                            }
                            movieAdapter = new MovieAdapter(getContext(), movieList);
                            recyclerView.setAdapter(movieAdapter);
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        movieAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
        void onFragmentInteraction(Uri uri);
    }
}
