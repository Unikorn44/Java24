package fr.m2i.models;

import java.util.List;

public interface ActorDao {
	void ajouter( Actor actor );
    List<Actor> lister();
}
