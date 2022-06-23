import java.lang.Math;

import javax.lang.model.util.ElementScanner6;

public class JeuDeLaVie{
	
int tailleGrille;
int[][] grille;
/**
 * Constructeur de la classe
 * @param taille qui represente la (taille * taille) de la grille
 */
public JeuDeLaVie(int taille){
	this.tailleGrille=taille;
}

/**
 * initialiser la grille avec des cellules mortes et vivantes au hasard
 */
public void initialiser(){
	grille = new int[tailleGrille][tailleGrille];
	int nbrAleatoire;
	for(int i=0;i<tailleGrille;i++){
		for(int j=0;j<tailleGrille;j++){
			nbrAleatoire = (int)(Math.random()+0.50);
			grille[i][j] = nbrAleatoire;
		}
	}
}
/**
 * La fonction retourne le nombre de voisins vivants de la cellule i et j
 * @param i indice (ligne de la grille)
 * @param j indice (colonne de la grille)
 * @return le nombre de voisins vivants de la cellule i et j
 */
private int nbrVoisins(int i, int j){
	int nbrVoisins = 0 ;
	/*if (i > 0 && j < tailleGrille){	
		if(grille[i-1][j-1] ==0 || grille[i-1][j-1] ==1){nbrVoisins++;}
		if(grille[i-1][j+1] == 0 || grille[i-1][j+1] == 1){nbrVoisins++;}
	}
	if (i > 0){
		if(grille[i-1][j] == 0 || grille[i-1][j] == 1){nbrVoisins++;}
	}
	if (j > 0 && j < tailleGrille){
		if(grille[i][j-1] == 0 || grille[i][j-1] == 1){nbrVoisins++;}
	}
	if (j < tailleGrille)
	if ( i < tailleGrille && j > 0){
		if(grille[i][j+1] == 0 || grille[i][j+1] == 1){nbrVoisins++;}
		if(grille[i+1][j-1] == 0 || grille[i+1][j-1] == 1){nbrVoisins++;}
		if(grille[i+1][j] == 0 || grille[i+1][j] == 1){nbrVoisins++;}
		if(grille[i+1][j+1] == 0 || grille[i+1][j+1] == 1){nbrVoisins++;}
	}*/
	if(i-1 >= 0 && j-1 >= 0 ){if( grille[i-1][j-1]==1)nbrVoisins++;}
	if(i-1 >= 0 && j+1 < tailleGrille){if(grille[i-1][j+1]==1)nbrVoisins++;}
	
	if(i-1 >= 0){if(grille[i-1][j]==1)nbrVoisins++;}
	if(j-1 >= 0 ){if(grille[i][j-1]==1)nbrVoisins++;}
	
	if(j+1 < tailleGrille){if(grille[i][j+1]==1)nbrVoisins++;}
	if(i+1 < tailleGrille && j-1 >= 0 ){if(grille[i+1][j-1]==1)nbrVoisins++;}
	
	if(i+1 < tailleGrille ){ if(grille[i+1][j]==1)nbrVoisins++;}
	if(i+1 < tailleGrille && j+1 < tailleGrille )
    {
        if(grille[i+1][j+1]==1) nbrVoisins++;
    }

	return nbrVoisins;
}
/**
 * donner une representation de la grille en affichant les cellules vivantes comme "*" et les mortes comme "."
 * @return la presentation de la grille
 */
public String toString(){
	int saut = 0 ;
	StringBuilder ch = new StringBuilder("");
	
	for(int i=0;i<tailleGrille;i++){
		for(int j=0;j<tailleGrille;j++){
			if(saut == tailleGrille){
				//System.out.print("\n");
				ch.append("\n");
				saut=0;
			}
			//System.out.print(grille[i][j]);
			if(grille[i][j]==1) ch.append("* ");
			if(grille[i][j]==0) ch.append(". ");
			//ch.append(""+grille[i][j]); 
			saut++;
		}
	}
	//System.out.print("\n");
	ch.append("\n");

	return ch.toString();
}

/**
 * permettra de calculer la nouvelle generation de cellules
 */

public void suivant()
{
	for(int i=0;i<tailleGrille;i++){
		for(int j=0;j<tailleGrille;j++){
			if(nbrVoisins(i, j)==3 && grille[i][j]==0){
				grille[i][j]=1;
			}
			else if((nbrVoisins(i, j)==3 || nbrVoisins(i, j)==2) && grille[i][j]==1){
				grille[i][j]=1;
			}
			else{
				grille[i][j]=0;
			}
		}
	}
}

/**
 * permet de tester si deux grilles sont egaux ou non
 * @param grille2 la grille avec laquelle on vas comparer la grille principale
 * @return 1 si les deux grilles sont egaux, 0 sinon
 */

public int egal(int [][] grille2){
	for(int i=0;i<tailleGrille;i++){
		for(int j=0;j<tailleGrille;j++){
			if(grille[i][j] != grille2[i][j]) return 0 ;
		}
	}
	return 1;
}

/**
 * modifier la grille jusqu'a trouver une etat stable de la grille (la grille ne change plus)
 */

public void resolution(){
	int [][] grille2 = new int[tailleGrille][tailleGrille];
	System.out.println(this.toString());
	while(this.egal(grille2)==0){
		for(int i=0;i<tailleGrille;i++){
			for(int j=0;j<tailleGrille;j++){
				grille2[i][j] = this.grille[i][j] ;
			}
		}
		this.suivant();
		System.out.println(this.toString());
	}
}

/**
 * Le bloc a la particularite d'etre une structure stable : il ne change pas d'une generation a l'autre
 * @return le bloc
 */

public JeuDeLaVie bloc(){
	for(int i=0;i<tailleGrille;i++){
		for(int j=0;j<tailleGrille;j++){
			this.grille[i][j] = 0 ;
		}
	}
	this.grille[(int)tailleGrille/2][(int)tailleGrille/2] = 1;
	this.grille[(int)tailleGrille/2][(int)tailleGrille/2 - 1] = 1;
	this.grille[(int)tailleGrille/2 - 1][(int)tailleGrille/2] = 1;
	this.grille[(int)tailleGrille/2 - 1][(int)tailleGrille/2 - 1] = 1;

	return this;

}

/**
 * La grenouille a la particularite d'etre une figure qui evolue et retrouve son aspect original (c'est un oscillateur) au bout de 2 generations.
 * @return la grenouille
 */

public JeuDeLaVie grenouille(){
	for(int i=0;i<tailleGrille;i++){
		for(int j=0;j<tailleGrille;j++){
			this.grille[i][j] = 0 ;
		}
	}
	this.grille[(int)tailleGrille/2][(int)tailleGrille/2 - 1] = 1;
	this.grille[(int)tailleGrille/2][(int)tailleGrille/2 - 2] = 1;
	this.grille[(int)tailleGrille/2][(int)tailleGrille/2] = 1;
	this.grille[(int)tailleGrille/2 - 1][(int)tailleGrille/2] = 1;
	this.grille[(int)tailleGrille/2 - 1][(int)tailleGrille/2 - 1] = 1;
	this.grille[(int)tailleGrille/2 - 1][(int)tailleGrille/2 + 1] = 1;

	return this;

}

/**
 * La planeur a la particularite d'etre une figure qui evolue et retrouve son aspect original a une position differente (c'est un vaisseau).
 * @return le planneur
 */

public JeuDeLaVie planeur(){
	for(int i=0;i<tailleGrille;i++){
		for(int j=0;j<tailleGrille;j++){
			this.grille[i][j] = 0 ;
		}
	}
	this.grille[(int)tailleGrille/2 - 1][(int)tailleGrille/2 - 1] = 1;
	this.grille[(int)tailleGrille/2 - 2][(int)tailleGrille/2 - 1] = 1;
	this.grille[(int)tailleGrille/2-1][(int)tailleGrille/2 + 1] = 1;
	this.grille[(int)tailleGrille/2][(int)tailleGrille/2 - 1] = 1;
	this.grille[(int)tailleGrille/2][(int)tailleGrille/2] = 1;

	return this;

}

public static void main(String[] args){
		JeuDeLaVie jeu = new JeuDeLaVie(10);
		jeu.initialiser();
		//jeu.bloc();
		//jeu.grenouille();	
		//jeu.planeur();
		jeu.resolution();
}
}