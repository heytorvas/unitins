import 'package:flutter/material.dart';
import 'package:frite/pages/examining_list.dart';
import 'package:frite/pages/palographic_list.dart';
import 'package:frite/pages/psychologist_list.dart';

class MainMenu extends StatefulWidget {
  const MainMenu({ Key? key }) : super(key: key);

  @override
  _MainMenuState createState() => _MainMenuState();
}

class _MainMenuState extends State<MainMenu> {
  GlobalKey<FormState> globalFormKey = new GlobalKey<FormState>();
  final scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: scaffoldKey,
      backgroundColor: Theme.of(context).accentColor,
      body: SingleChildScrollView(
        child: Column(children: <Widget>[
          Stack(
            children: <Widget>[
              Container(
                child: Align(
                  alignment: Alignment.center,
                  child: Text("FRITE",
                      style: TextStyle(
                        color: Theme.of(context).primaryColor,
                        fontSize: 40.0,
                        fontWeight: FontWeight.w800,
                      )),
                ),
                padding: EdgeInsets.symmetric(vertical: 30, horizontal: 20),
                margin: EdgeInsets.symmetric(vertical: 85, horizontal: 20),
              ),
              Container(
                width: double.infinity,
                padding: EdgeInsets.symmetric(vertical: 30, horizontal: 20),
                margin: EdgeInsets.symmetric(vertical: 200, horizontal: 20),
                child: Form(
                  key: globalFormKey,
                  child: Column(
                    children: <Widget>[
                      SizedBox(
                        width: double.infinity,
                        child: TextButton(
                          style: TextButton.styleFrom(
                            backgroundColor: Theme.of(context).primaryColor,
                            shape: StadiumBorder(),
                            padding: EdgeInsets.symmetric(
                                vertical: 12, horizontal: 68),
                          ),
                          onPressed: () {
                            Navigator.push(
                              context, 
                              MaterialPageRoute(builder: (context) => PsychologistList()));
                          },
                          child: Text("Psicólogo",
                            style: Theme.of(context).textTheme.headline2
                          ),
                        )
                      ),

                      SizedBox(height: 20),
                      SizedBox(
                        width: double.infinity,
                        child: TextButton(
                          style: TextButton.styleFrom(
                            backgroundColor: Theme.of(context).primaryColor,
                            shape: StadiumBorder(),
                            padding: EdgeInsets.symmetric(
                                vertical: 12, horizontal: 68),
                          ),
                          onPressed: () {
                            Navigator.push(
                              context, 
                              MaterialPageRoute(builder: (context) => ExaminingList()));
                          },
                          child: Text("Examinado",
                            style: Theme.of(context).textTheme.headline2
                          ),
                        )
                      ),

                      SizedBox(height: 20),
                      SizedBox(
                        width: double.infinity,
                        child: TextButton(
                          style: TextButton.styleFrom(
                            backgroundColor: Theme.of(context).primaryColor,
                            shape: StadiumBorder(),
                            padding: EdgeInsets.symmetric(
                                vertical: 12, horizontal: 68),
                          ),
                          onPressed: () {
                            Navigator.push(
                              context, 
                              MaterialPageRoute(builder: (context) => PalographicList()));
                          },
                          child: Text("Palográfico",
                            style: Theme.of(context).textTheme.headline2
                          ),
                        )
                      ),

                      SizedBox(height: 20),
                      SizedBox(
                        width: double.infinity,
                        child: TextButton(
                          style: TextButton.styleFrom(
                            backgroundColor: Theme.of(context).primaryColor,
                            shape: StadiumBorder(),
                            padding: EdgeInsets.symmetric(
                                vertical: 12, horizontal: 68),
                          ),
                          onPressed: () {},
                          child: Text("Análise",
                            style: Theme.of(context).textTheme.headline2
                          ),
                        )
                      )
                      
                    ]
                  ),
                ),
              )
            ],
          )
        ],),
      )
    );
  }
}