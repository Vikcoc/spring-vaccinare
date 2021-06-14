package com.vaccin.vaccin.controller;

import com.vaccin.vaccin.dto.ArticleDto;
import com.vaccin.vaccin.dto.ArticleListingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArticlesController {


    @GetMapping("/articles")
    public ResponseEntity<List<ArticleListingDto>> articles() {
        return new ResponseEntity<>(articlesL, HttpStatus.OK);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleDto> article(@PathVariable int id) {
        if (id < 1 || id > 6)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(articles.get(id - 1), HttpStatus.OK);
    }

    public ArticlesController(){
        List<ArticleDto> art = new ArrayList<>();
        art.add(new ArticleDto(
                1,
                "https://images.unsplash.com/photo-1560264280-88b68371db39?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80",
                "Asistenta Covid-19",
                "Directia De Sanatate Publica\n" +
                        "Call Center national 021.9462\n\n" +
                        "LINII TELEFONICE\n" +
                        "Tel Verde 1: 021.642.22.26\u200B\n" +
                        "Tel Verde 2: 021.642.28.29\u200B\n" +
                        "Tel Verde 3: 021.642.20.07\n" +
                        "Tel Verde 4: 021.642.20.08",
                1,
                1
        ));

        art.add(new ArticleDto(
                2,
                "https://cdn1.vectorstock.com/i/1000x1000/39/10/coughing-man-vector-6253910.jpg",
                "Simptome",
                "COVID-19 afectează persoanele în moduri diferite. Majoritatea persoanelor infectate vor dezvolta o formă ușoară sau moderată a bolii și se vor recupera fără spitalizare.\n" +
                        "\n" +
                        "\n" +
                        "Cele mai frecvente simptome:\n" +
                        "febră,\n" +
                        "tuse seacă,\n" +
                        "oboseală.\n" +
                        "\n" +
                        "\n" +
                        "Simptome mai puțin frecvente:\n" +
                        "dureri de cap și dureri musculare,\n" +
                        "durere în gât,\n" +
                        "diaree,\n" +
                        "conjunctivită,\n" +
                        "dureri de cap,\n" +
                        "pierderea gustului sau a mirosului,\n" +
                        "o iritație la nivelul pielii sau o decolorare a degetelor de la mâini sau de la picioare.\n" +
                        "\n" +
                        "\n" +
                        "Simptome grave:\n" +
                        "dificultăți de respirație sau dispnee,\n" +
                        "durere sau presiune toracică,\n" +
                        "incapacitatea de a vorbi sau de a se mișca.\n" +
                        "\n" +
                        "\n" +
                        "Dacă prezentați simptome grave, consultați imediat medicul. Sunați în prealabil.\n" +
                        "\n" +
                        "Persoanele cu simptome ușoare care nu au alte probleme de sănătate trebuie să se autoizoleze.\n" +
                        "\n" +
                        "Din momentul în care o persoană este infectată cu virusul, durează în medie 5-6 zile până să apară simptomele; totuși, poate dura și până la 14 zile.",
                5,
                1
        ));

        art.add(new ArticleDto(
                3,
                "https://www.statista.com/graphic/1/1104612/romania-confirmed-covid-19-cases.jpg",
                "Statistici 10 iunie 2021:",
                    "Mondial:\n" +
                            "\n" +
                            "174 mil. infectări \n" +
                            "3.76 mil. decese\n" +
                            "\n" +
                            "România:\n" +
                            "1.08 mil. infectări \n" +
                            "1.04 mil. s-au vindecat\n" +
                            "31 383 decese",
                1,
                2
                ));

        art.add(new ArticleDto(
                4,
                "https://media.msf.org/AssetLink/23w4642dl172x0u8jhsi6w10sh42080m.jpg",
                "Tratamente",
                "Îngrijire personală\n" +
                        "\n" +
                        "După contactul cu o persoană infectată cu COVID-19, procedați astfel:\n" +
                        "Contactați medicul sau linia de asistență pentru COVID-19 pentru a afla unde și când vă puteți testa.\n" +
                        "Cooperați în cadrul procedurilor de urmărire a contactelor pentru a opri răspândirea virusului.\n" +
                        "Dacă nu este posibilă testarea, stați acasă și izolați-vă de alte persoane timp de 14 zile.\n" +
                        "Cât timp sunteți în carantină, nu mergeți la serviciu, la școală sau în locuri publice. Rugați pe cineva să vă aducă lucrurile de care aveți nevoie.\n" +
                        "Mențineți o distanță de cel puțin un metru față de alte persoane, chiar și față de membrii familiei.\n" +
                        "Purtați mască medicală pentru a-i proteja pe ceilalți, inclusiv dacă / când trebuie să mergeți la medic.\n" +
                        "Spălați-vă frecvent pe mâini.\n" +
                        "Stați într-o cameră separată de ceilalți membrii ai familiei sau, dacă nu este posibil, purtați o mască medicală.\n" +
                        "Aerisiți bine camera.\n" +
                        "Dacă împărțiți camera cu alte persoane, așezați paturile la o distanță de cel puțin un metru între ele.\n" +
                        "Monitorizați-vă simptomele timp de 14 zile.\n" +
                        "Contactați imediat medicul dacă prezentați unul dintre aceste simptome periculoase: dificultăți de respirație, incapacitatea de a vorbi sau de a vă mișca, confuzie sau durere toracică.\n" +
                        "Mențineți o atitudine pozitivă păstrând legătura cu persoanele dragi prin telefon sau online și făcând mișcare acasă.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Tratamente medicale\n" +
                        "Oamenii de știință din întreaga lume se străduiesc să găsească și să dezvolte tratamente pentru COVID-19.\n" +
                        "Îngrijirea optimă include oxigen pentru pacienții cu forme mai grave ale bolii și pentru cei care prezintă riscul dezvoltării de boli grave și asistență respiratorie mai avansată, precum ventilație pentru pacienții în stare critică.\n" +
                        "Dexametazona este un corticosteroid care poate ajuta la reducerea timpului de folosire a unui ventilator și la salvarea vieților pacienților în stare gravă sau critică.\n" +
                        "\n" +
                        "OMS nu recomandă automedicația cu niciun tip de medicament, inclusiv antibiotice, ca măsură de prevenție sau tratament pentru COVID-19.",
                7,
                1
        ));

        art.add(new ArticleDto(
                5,
                "https://images.theconversation.com/files/22820/original/z3kh69z2-1366768139.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=926&fit=clip",
                "De ce să mă vaccinez împotriva COVID-19?",
                "Vaccinarea împotriva COVID-19 este importantă pentru că reduce riscul de îmbolnăvire și riscul de a dezvolta o formă severă de boală. Vaccinarea este un instrument important pentru a opri răspândirea pandemiei, scădea supraîncărcarea unităților sanitare, permițând, astfel, redeschiderea unităților sanitare pentru pacienții non-COVID. Astfel, vor fi create condițiile pentru redeschiderea activităților socio-economice și înlesnirea liberei circulații.\n" +
                        "\n" +
                        "\n" +
                        "Cum sunt protejate datele cu caracter personal privind vaccinarea împotriva COVID-19?\n" +
                        "Monitorizarea nivelului de acoperire vaccinală, a siguranței și eficacității vaccinurilor împotriva COVID-19 se realizează cu ajutorul platformelor electronice, respectându-se legislația cu privire la protecția datelor cu caracter personal. Conform strategiei privind vaccinarea împotriva COVID-19 în România se va dezvolta un modul dedicat vaccinării împotriva COVID-19 în cadrul Registrului Electronic Național de Vaccinare (RENV), care va constitui baza de date electronică referitoare la vaccinare, cu respectarea prevederilor legale privind protecția datelor cu caracter personal.\n" +
                        "\n" +
                        "\n" +
                        "Ce document se va elibera în momentul vaccinării?\n" +
                        "Fiecare persoană care se va vaccina va primi o adeverință doveditoare de vaccinare care va cuprinde următoarele informații: numele și prenumele persoanei vaccinate, data nașterii, vârsta, județul de domiciliu, număr și serie CI, date despre vaccin: doza 1 (tip vaccin, produs, serie/lot, data expirării), doza 2 (tip vaccin, produs, serie/lot, data expirării), date despre centrul de vaccinare (nume centru de vaccinare, județ, medic coordonator/vaccinator), semnătura electronică a Registrului Electronic Național al Vaccinărilor.\n" +
                        "Dovada vaccinării este pusă la dispoziția persoanei vaccinate fie electronic, fie pe suport de hârtie pentru a-i permite acesteia să își țină evidența între cele două vizite la centrul de vaccinare (doză inițială și rapel) și pentru a-i pune la dispoziție informațiile esențiale despre caracteristicile vaccinului administrat. Precizăm că vaccinarea este gratuită, voluntară/ neobligatorie, iar adeverința doveditoare nu este eliberată cu scopul de  a condiționa sau restricționa ulterior drepturile persoanelor vaccinate.\n" +
                        "\n" +
                        "\n" +
                        "Dacă am fost infectat cu SARS-CoV-2, mai este necesară vaccinarea?\n" +
                        "În studiile clinice, vaccinul a fost administrat și unor persoane care prezentau un nivel detectabil de anticorpi anti SARS-CoV-2 și nu s-au detectat efecte adverse, ci doar o creștere a nivelului de răspuns imun.  Vaccinarea este disponibilă, pe baza consimțământului, indiferent dacă a existat sau nu infectarea.\n" +
                        "\n" +
                        "\n" +
                        "Persoanele nevaccinate le pot infecta pe cele vaccinate?\n" +
                        "O persoană nevaccinată se poate infecta cu virusul SARS-CoV-2 și-l poate transmite celor cu care intră în contact. Contacții vaccinați (vaccin Pfizer/BioNTech, aprobat condiționat în Uniunea Europeană) ai persoanei nevaccinate sunt protejați față de de dezvoltarea bolii Covid-19 – chiar dacă virusul ar putea ajunge în căile respiratorii, transmis de la un purtător, nu va produce efecte, fiind inactivat de anticorpii protectori dezvoltați după vaccinare.\n" +
                        "\n" +
                        "\n" +
                        "Persoanele vaccinate le pot infecta pe cele nevaccinate?\n" +
                        "Obiectivul principal al studiului de faza 3 pentru vaccinul Pfizer/BioNTech, aprobat condiționat în Uniunea Europeană, nu a presupus măsurarea transmisibilității SARS-CoV-2 după vaccinare, ci demonstrarea eficacității pentru prevenirea bolii Covid-19. Date despre măsura în care o persoană vaccinată mai poate transmite virusul urmează a fi generate, colectate și analizate în perioada următoare. Experiența cu alte vaccinuri arată că în general o persoană vaccinată transmite într-o mai mică măsură agentul patogen, dar această ipoteză trebuie validată și pentru SARS-CoV-2.\n" +
                        "\n" +
                        "\n" +
                        "De unde mă pot informa cu privire la vaccinurile candidate?\n" +
                        "La nivelul Uniunii Europene, vaccinurile care vor fi folosite în campaniile de vaccinare împotriva COVID-19 sunt cele care au primit aprobarea din partea Agenției Europene a Medicamentului. Toate detaliile cu privire la procesul de evaluare și aprobare sunt disponibile și actualizate constant pe site-ul Agenției Europene a Medicamentului (https://www.ema.europa.eu/en/human-regulatory/overview/public-health-threats/coronavirus-disease-covid-19/treatments-vaccines/covid-19-vaccines-development-evaluation-approval-monitoring).\n" +
                        "\n" +
                        "\n" +
                        "Cu cine iau legătura dacă vreau să mă vaccinez?\n" +
                        "Conform Strategiei pentru vaccinarea împotriva COVID-19 în România, vaccinarea se va realiza în etape, respectând grupele populaționale. Pentru etapa I, vaccinarea se va organiza la locul de muncă și prin centrele de vaccinare fixe și mobile, iar pentru etapele II și III vaccinarea se va organiza prin centre de vaccinare fixe și mobile, echipe mobile, rețeaua de medicină de familie, centre drive-through.\n" +
                        "\n" +
                        "\n" +
                        "Pot refuza să mă vaccinez?\n" +
                        "Da, conform strategiei pentru vaccinarea împotriva COVID-19 în România vaccinarea este voluntară și ne-obligatorie.\n" +
                        "\n" +
                        "\n" +
                        "Vaccinarea este gratuită?\n" +
                        "Da, conform strategiei pentru vaccinarea împotriva COVID-19 în România vaccinarea este gratuită.\n" +
                        "\n" +
                        "\n" +
                        "Dacă mă vaccinez, mai este necesar să respect celelalte măsuri igienico-sanitare?\n" +
                        "Administrarea unui vaccin împotriva COVID-19 reprezintă unul dintre instrumentele critice pentru limitarea efectelor pandemiei, alături de celelalte instrumente: respectarea restricțiilor și a măsurilor igienico-sanitare (purtarea măștii, spălatul pe mâini și distanțarea fizică).\n" +
                        "\n" +
                        "\n" +
                        "Persoanele cu condiții medicale preexistente sunt eligibile pentru vaccinare?\n" +
                        "Conform strategiei naționale de vaccinare împotriva COVID-19, persoanele aflate în evidență cu boli cronice, indiferent de vârstă, sunt prioritare în vederea vaccinării, luându-se în considerație indicațiile și contraindicațiile vaccinurilor utilizate.\n" +
                        "\n" +
                        "\n" +
                        "O persoană care a fost infectată cu SARS-CoV-2 se poate vaccina?\n" +
                        "Da, o persoana care a fost infectată cu SARS-CoV-2 poate fi vaccinată în mod voluntar, atunci când întrunește criteriile legate de prioritizare. Nu se are în vedere excluderea de la vaccinare a persoanelor cu infecție anterioară cunoscută și nici testarea persoanelor înaintea vaccinării.\n" +
                        "În momentul de față nu se știe pentru cât timp după infecție suntem protejați și nici nivelul de anticorpi necesar pentru a fi protejați. Studii recente(https://science.sciencemag.org/content/early/2020/10/27/science.abd7728) arată că la majoritatea persoanelor, anticorpii neutralizanți (cei care pot bloca infectarea celulelor) persistă cel puțin câteva luni de la infecția inițială. De asemenea, studiile arată că răspunsul imun în urma infecției naturale poate varia foarte mult în funcție de individ și de severitatea bolii.\n" +
                        "Autoritățile de reglementare(https://www.fda.gov/media/139638/download) nu recomandă excluderea participanților la studiile clinice din Faza 3 pe baza dovezilor despre o infecție anterioară astfel încât majoritatea producătorilor au vaccinat deja și persoane care au trecut prin boală(https://www.bmj.com/content/bmj/371/bmj.m4058.full.pdf), fără a fi raportate reacții adverse severe.\n" +
                        "\n" +
                        "\n" +
                        "Ce sunt centrele de vaccinare drive-through?\n" +
                        "Centrele de vaccinare drive-through sunt centre de vaccinare fixe amenajate în mod special pentru etapa a III-a a procesului de vaccinare care este adresată populației generale. Conform recomandărilor Organizației Mondiale a Sănătății, organizarea și amenajarea  acestor centre se vor face în marile aglomerări urbane, în zone care permit accesul persoanelor cu mijloace auto proprii. Organizarea și amenajarea centrelor de vaccinare drive-through se vor face cu respectarea tuturor normelor de siguranță și a normelor igienico-sanitare, pentru a facilita accesul persoanelor la vaccinare, a eficientiza procesul de vaccinare, a evita supraaglomerarea sau sacrificarea dozelor de vaccin. Organizarea unor astfel de centre drive-through este propusă prin strategia Organizației Mondiale a Sănătății(https://apps.who.int/iris/handle/10665/335940).\n" +
                        "\n" +
                        "\n" +
                        "Ce sunt centrele mobile de vaccinare?\n" +
                        "Centrele mobile de vaccinare sunt centre de vaccinare/caravane mobile organizate în diferite etape ale procesului de vaccinare. Organizarea și amenajarea  acestor centre mobile de vaccinare se vor face, atunci când este necesar, pentru a facilita accesul la vaccinare al persoanelor ce deservesc activități esențiale, al persoanelor aflate în centre rezidențiale și medico-sociale și, după caz, al persoanelor din rândul populației generale pentru care accesul la vaccinare este limitat. Organizarea și amenajarea centrelor mobile de vaccinare se vor face cu respectarea tuturor normelor de siguranță și a normelor igienico-sanitare, pentru a facilita accesul persoanelor la vaccinare, a eficientiza procesul de vaccinare, a evita supraaglomerarea sau sacrificarea dozelor de vaccin.\n" +
                        "\n" +
                        "\n" +
                        "Ce  este o autorizație condiționată  de punere pe piață (CMA)?\n" +
                        "În cadrul Uniunii Europene, o autorizație condiționată de punere pe piață permite autorizarea unor medicamente care se adresează unei nevoi neacoperite încă, pe baza unor date mai puțin complete decât cele solicitate în mod uzual. Acest lucru se întâmplă în condiții de urgență de sănătate publică, precum pandemia de Covid-19, doar dacă beneficiile cântăresc mult mai mult decât posibilele riscuri create de faptul că nu sunt disponibile încă toate datele. Autorizațiile condiționate sunt utilizate în cazul pandemiei de Covid-19 pentru a răspunde urgenței legate de sănătatea publică. Chiar și așa, datele furnizate trebuie să indice în mod clar faptul că beneficiile medicamentului sau ale vaccinului prevalează în fața posibilelor riscuri. Cerința ca beneficiile să fie mult mai mari în raport cu posibilele riscuri este cu atât mai explicită în cazul vaccinurilor ce se vor administra persoanelor sănătoase. O dată ce o astfel de autorizație condiționată a fost emisă, companiile trebuie să furnizeze mai multe date, din studii aflate în desfășurare sau din studii noi, la termene prestabilite, pentru a demonstra, în continuare, că beneficiile sunt mai mari decât riscurile.\n" +
                        "\n" +
                        "\n" +
                        "Ce se întâmplă după ce o companie depune cererea de autorizare de punere pe piață a unui vaccin? \n" +
                        "În cazul în care Agenția Europeană a Medicamentului va ajunge la concluzia că beneficiile vaccinului sunt mai mari decât riscurile, va recomanda Comisiei Europene acordarea autorizației condiționate de punere pe piață. Comisia va accelera apoi procedurile de decizie și, în câteva zile, autorizația ar putea fi adoptată la nivelul statelor membre UE și al statelor EEA.\n" +
                        "Ca și pentru alte medicamente, autoritățile de reglementare ale UE vor continua să adune și să evalueze noi informații și după punerea pe piață și să ia măsuri dacă și atunci când este nevoie. Respectând planul european pentru monitorizarea siguranței vaccinurilor împotriva COVID-19, monitorizarea se va face mai des și va include activități specifice pentru aceste vaccinuri. De exemplu, în plus față de actualizările periodice cerute de legislație, companiile vor furniza rapoarte lunare care monitorizează siguranța și vor realiza studii pentru monitorizarea siguranței și eficacității vaccinurilor COVID-10, chiar și după autorizarea lor.  Aceste măsuri le vor permite autorităților de reglementare să evalueze date care provin din surse diferite și să ia măsurile care se impun pentru protejarea sănătății publice, dacă este cazul.\n" +
                        "\n" +
                        "\n" +
                        "Cum funcționează vaccinurile pentru care au fost depuse cereri de obținere a autorizațiilor de punere pe piață?  (BNT162b2 și RNA1273)\n" +
                        "Vaccinurile vor pregăti organismul uman să se apere împotriva COVID-19. Pentru a pătrunde în organism și pentru a cauza boala, virusul SARS-CoV-2 utilizează o proteină aflată pe învelișul său, proteină denumită proteina S (spike). Vaccinurile conțin instrucțiunile genetice (ARN mesager) necesare pentru generarea proteinei S. Când o persoană este vaccinată, celulele sale vor ”citi” instrucțiunile genetice din ARN-ul mesager și vor sintetiza proteina S. Sistemul imun al persoanei respective va recunoaște această proteină ca fiind străină și va declanșa mecanismele de apărare imună, producând anticorpi  și celule T specifice. Dacă mai târziu, persoana vaccinată va intra în contact cu virusul SARS-CoV-2, sistemul său imun va recunoaște virusul și, prin intermediul anticorpilor și al celulelor T specifice, va neutraliza virusul, prevenind, astfel, intrarea în celulele sănătoase ale corpului și distrugând eventualele celule infectate. Astfel, se va asigura protecția împotriva COVID-19.",
                10,
                2
        ));

        art.add(new ArticleDto(
                6,
                "https://image.slidesharecdn.com/unit7dailyroutinesunit7-130226134559-phpapp01/95/daily-routines-unit-7-1-638.jpg?cb=1361887656",
                "Gestionarea situației",
                "Menajați-vă mintea\n" +
                        "Problemele de sănătate mintală sunt frecvente. Iată câteva sfaturi pentru reducerea stresului și întreținerea sănătății mintale\n" +
                        "\n" +
                        "Pauză. Respirați. Reflectați\n" +
                        "Inspirați lent de câteva ori: inspirați pe nas, apoi expirați lent.\n" +
                        "\n" +
                        "Respirația lentă este una dintre cele mai bune modalități de a reduce stresul, deoarece îi semnalează creierului să relaxeze corpul.\n" +
                        "\n" +
                        "Observați cum vă simți și ce gândiți, fără a analiza. În loc să răspundeți sau să reacționați la gândurile sau sentimentele respective, conștientizați-le și eliberați-vă de ele.\n" +
                        "\n" +
                        "\n" +
                        "Vorbiți cu alte persoane\n" +
                        "Poate fi util să vorbiți cu persoane de încredere. Păstrați regulat legătura cu persoanele apropiate. Spuneți-le cum vă simți și ce îngrijorări aveți.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Mențineți o rutină sănătoasă\n" +
                        "Ce să faceți:\n" +
                        "\n" +
                        "Treziți-vă și culcați-vă la aproximativ aceleași ore în fiecare zi.\n" +
                        "Mențineți rutina de igienă personală.\n" +
                        "Mâncați mese sănătoase, la ore fixe.\n" +
                        "Faceți mișcare în mod regulat. Doar 3 – 4 minute de mișcare fizică ușoară, cum ar fi mersul sau întinderile, vă vor ajuta.\n" +
                        "Alocați timp pentru lucru și timp pentru odihnă.\n" +
                        "Faceți-vă timp pentru lucrurile care vă plac.\n" +
                        "Luați pauze regulate de la activitățile în fața ecranului.\n" +
                        "Ce să nu faceți:\n" +
                        "\n" +
                        "Nu consumați alcool sau droguri ca o modalitate de a face față fricii, anxietății, plictiselii și izolării sociale.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Fiți bun cu dvs. și cu ceilalți\n" +
                        "Nu aveți așteptări prea mari de la dumneavoastră în zilele dificile. Acceptați faptul că unele zile ar putea fi mai productive decât altele.\n" +
                        "\n" +
                        "Încercați să reduceți cantitatea de știri urmărite, citite sau ascultate care vă cauzează anxietate sau suferință. Căutați cele mai recente informații din surse de încredere, în anumite momente ale zilei.\n" +
                        "\n" +
                        "Ajutorul oferit altor persoane vă poate face bine. Dacă puteți, oferiți asistență persoanelor din comunitatea dumneavoastră care au nevoie.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Cereți ajutor când este necesar\n" +
                        "Nu ezitați că solicitați ajutorul unui profesionist dacă sunteți de părere că este necesar. Un bun punct de plecare este profesionistul local în domeniul medical. Și liniile de asistență pot fi o sursă de ajutor.",
                8,
                1
        ));

        articles = art;
        articlesL = art.stream().map(ArticleListingDto::new).collect(Collectors.toList());
    }

    private final List<ArticleListingDto> articlesL;
    private final List<ArticleDto> articles;
}
