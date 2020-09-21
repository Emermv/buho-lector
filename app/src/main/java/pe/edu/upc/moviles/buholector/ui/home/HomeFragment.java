package pe.edu.upc.moviles.buholector.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import pe.edu.upc.moviles.buholector.R;
import pe.edu.upc.moviles.buholector.data.Constants;
import pe.edu.upc.moviles.buholector.data.entity.request.Http;
import pe.edu.upc.moviles.buholector.model.BookModel;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private RecyclerView recyclerView;
    private boolean loading=false,searching=false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

      /*  final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
       recyclerView = root.findViewById(R.id.book_list_recycler_view);
        recyclerView.setLayoutManager( new GridLayoutManager(getContext(),2));
        //recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        init(Constants.GET_BOOK);
        return root;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("Libros");
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {


                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    if(!newText.isEmpty()){
                        searching=true;
                        init(Constants.FIND_BOOK+"?buscar="+newText);
                    }else{
                        searching=false;
                        init(Constants.GET_BOOK);
                    }

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    if(query.isEmpty()){
                        init(Constants.GET_BOOK);
                        searching=false;
                    }else{
                        searching=true;
                        init(Constants.FIND_BOOK+"?buscar="+query);
                    }
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }
    private void init(String url){
        if(!loading){
            loading=true;

            Http.request(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    loading=false;
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    loading=false;
                    if(response.isSuccessful()){
                        //Log.i("LIZ",response.body().string());
                        //  String json=response.body().string().replaceAll("\"","\"");
                        String json=response.body().string();
                        try {
                            JSONObject object=new JSONObject(json);
                            JSONArray books_objetcs=object.getJSONArray("libros");
                            ArrayList<BookModel> books=new ArrayList<>();
                            for (int i=0;i<books_objetcs.length();i++){
                                JSONObject book=books_objetcs.getJSONObject(i);
                                BookModel model=new BookModel();
                                model.setId(book.getInt("idLibro"));
                                model.setAutor(book.optString("autor",""));
                                model.setPaginas(book.optInt("paginas",0));
                                model.setTitulo(book.getString("titulo"));
                                model.setEstado(book.optString("estado",""));
                                model.setPropietario(book.optInt("propietario",-1));
                                model.setResumen(book.optString("resumen",""));
                                model.setLenguaje(book.optString("lenguaje",""));
                                 model.setImagen(book.optString("imagen",""));
                               // model.setImagen("https://atmosferaweb.com/app/assets/img/news/68368.png");
                                books.add(model);
                                Log.i("LIZ"+i,model.toString());
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(new Adapter(books,searching));
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}