# Copyright Aldica Maria-Miruna, 321CA, 2023

DESIGN PATTERNS:

- Toata logica se afla in clasele de actiuni si in cele de pagini;
- Am realizat clasa PageContext careia i-am atribui design pattern-ul SINGLETON, deoarece avem un
singur context de pagina, adica cel curent;
- Am creat design patternul COMMAND pt Actions, deoarece acestea vor avea o metoda de execute()
pentru a se executa fiecare comanda in parte;
- Pentru paginile de tip OnPage am folosit patternul de VISITOR OnPage in comanda,
vizitam pagina respectva si aceasta realizeaza logica pentru partea sa;
- Am pastrat design patternul FACTORY, utilizat si in tema 2, etapa 1 a proiectului;


Main - acesta apeleaza functia statica solve ce incarca userii, actiunile,
dar si filmele;

Pachetul Data contine:
    - Pachetul Actions cu toate actiunile posibil realizabile, cateva dintre acestea fiind;
        - ActionType: enumerare a tipurilor existente de actiuni;
        - BackAction: actiune ce realizeaza back-ul si trecerea la;
        pagina precedenta, daca se poate realiza;
        - ChangePageAction: contine datele paginii pentru care se va folosi;
        o actiune de acest tip (la fel si pentru OnPageAction);
        - CangePageMovieAction: selecteaza filmul cautat;
        - Command: DESIGN PATTERN;
        - DatabaseAddAction: actiunea ce realizeaza notificarea de add;
        - DatabaseDeleteAction: actiunea ce realizeaza notificarea de delete;
        - FilterAction, FilterActionContains si FilterActionSort: clasele
        unde se afla actiunile folosite pentru filtrele paginilor;
        - MovieAction;
        - OnPageAction: contine actiunile de tip on page, precum si selectarea
        paginii curente;
        - Recommendation: actiunea de recomandare de filme pentru utilizatori;
        - SearchAction: actiune ce realizeaza cautarea dupa un sir de caractere;
        - SubscribeAction: actiunea de subscribe a utilizatorului;
        - UpgradesAction: actiunea de upgrade a utilizatorului;
        - UserPageAction;
    - Pachetul entities unde gasim:
        - AccountType: enumerarea tipurilor de cont ale utilizatorilor
        - Action: clasa abstracta;
        - Credentials;
        - Movie;
        - Notification;
        - Output : metoda ce retine datele ce se doresc afisate(vom avea erori,
        lista curenta de filme, precum si utilizatorul curent);
        - User;

Pachetul factories contine:
    - Design Patternul folosit de mine este Factory, utilizat in PageFactory,
precum si in InputDataFactory;
    - Se returneaza caracteristicile paginii in functie de numele acesteia;

Pachetul IO contine:
    - Pachetul input:
        - acesta contine InputData ce reprezinta o clasa ce retine
        datele ce se doresc a fi citite si incarcate in memorie, pentru a fi
        mai tarziu folosite in prelucrare, precum si deserializere-le;
        - se primeste valoarea fiecarui atribut(name, password, etc),
        dupa care in functie de feature-ul fiecarui obiect, se primesc campurile
        necesare pentru a fi toate atributele lor incarcate in memorie
        (astfel de prelucrari se realizeaza atat pentru useri, Credentials, Movie,
        cat si pentru Action);
        - pachetul output.serielizers: contine datele ce ni le dorim afisate la output;

Pachetul session contine:
    - Pachetul pages cu Clase ce reprezinta fiecare pagina pe care putem ajunge, precum si
     metodele lor;
    - DataBase, MoviesDatabase, UserDatabase reprezinta bazele de date in care retinem
    toate informatiile primite in memorie pe care uremaza sa le folosim;
    PageContext - contine toate atributele unei pagini si realizeaza o copie a acesteia;
    Runner - executam actiunile;


