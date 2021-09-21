import 'dart:io' as io;

import 'package:flutter/material.dart';
import 'package:frite/models/examining.dart';
import 'package:frite/models/palographic.dart';
import 'package:frite/models/psychologist.dart';
import 'package:frite/pages/main_menu.dart';
import 'package:frite/services/examining_service.dart';
import 'package:frite/services/palographic_service.dart';
import 'package:frite/services/psychologist_service.dart';
import 'package:image_picker/image_picker.dart';

class RegisterPalographic extends StatefulWidget {
  const RegisterPalographic({Key? key}) : super(key: key);

  @override
  _RegisterPalographicState createState() => _RegisterPalographicState();
}

class _RegisterPalographicState extends State<RegisterPalographic> {
  final scaffoldKey = GlobalKey<ScaffoldState>();
  GlobalKey<FormState> globalFormKey = new GlobalKey<FormState>();

  final schooling = ['Fundamental', 'Ensino Médio', 'Graduação', 'Mestrado', 'Doutorado', 'Pós-Doutorado'];
  String? _currentSchooling = 'Fundamental';

  late XFile _image;

  Future _getImage() async {
    var image = await ImagePicker().pickImage(source: ImageSource.gallery);
    setState(() {
      _image = image!;
    });
  }
 

  List<Examining> examining = [];
  late Examining _currentExamining;

  getExaminingList() async {
    List<Examining> list = await ExaminingService.findAll();
    setState(() {
      examining = list;
      _currentExamining = examining[0];
      print(examining);
    });
  }

  List<Psychologist> psychologist = [];
  late Psychologist _currentPsychologist;

  getPsychologistList() async {
    List<Psychologist> list = await PsychologistService.findAll();
    setState(() {
      psychologist = list;
      _currentPsychologist = psychologist[0];
      print(psychologist);
    });
  }

  @override
  void initState() {
    super.initState();
    getExaminingList();
    getPsychologistList();
  }  

  TextEditingController birthDateController = new TextEditingController();

  register() {
    PalographicService.createPalographic(
      _image, _currentExamining, _currentPsychologist, 
      birthDateController.text, _currentSchooling
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        key: scaffoldKey,
        backgroundColor: Theme.of(context).accentColor,
        body: SingleChildScrollView(
            child: Column(children: <Widget>[
          Stack(children: <Widget>[
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
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(20),
                    color: Theme.of(context).primaryColor,
                    boxShadow: [
                      BoxShadow(
                          color: Theme.of(context).hintColor.withOpacity(0.2),
                          offset: Offset(0, 10),
                          blurRadius: 20)
                    ]),
                child: Form(
                    key: globalFormKey,
                    child: Column(children: <Widget>[
                      SizedBox(height: 25),
                      Text("Palográfico",
                          style: Theme.of(context).textTheme.headline2),

                      // EXAMINADO
                      SizedBox(height: 20),
                      new DropdownButtonFormField<Examining>(
                        decoration: new InputDecoration(
                            labelText: "Escolha o examinado",
                            enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context)
                                        .accentColor
                                        .withOpacity(0.2))),
                            focusedBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context).accentColor)),
                            prefixIcon: Icon(Icons.person,
                                color: Theme.of(context).accentColor),
                        ),
                        items: examining.map((map) => 
                          DropdownMenuItem(
                            child: Text(map.name!),
                            value: map,
                          ),
                        ).toList(),
                        value: _currentExamining,
                        onChanged: (val) {
                          setState(() {
                            _currentExamining = val!;
                            print(_currentExamining);
                          });
                        } ,
                      ),

                      // PSICOLOGO
                      SizedBox(height: 20),
                      new DropdownButtonFormField<Psychologist>(
                        decoration: new InputDecoration(
                            labelText: "Escolha o psicólogo",
                            enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context)
                                        .accentColor
                                        .withOpacity(0.2))),
                            focusedBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context).accentColor)),
                            prefixIcon: Icon(Icons.health_and_safety,
                                color: Theme.of(context).accentColor),
                        ),
                        items: psychologist.map((map) => 
                          DropdownMenuItem(
                            child: Text(map.name!),
                            value: map,
                          ),
                        ).toList(),
                        value: _currentPsychologist,
                        onChanged: (val) {
                          setState(() {
                            _currentPsychologist = val!;
                            print(_currentPsychologist);
                          });
                        } ,
                      ),

                      // BIRTH DATE
                        SizedBox(height: 20),
                        new TextFormField(
                          controller: birthDateController,
                          keyboardType: TextInputType.datetime,
                          decoration: new InputDecoration(
                            hintText: "Insira a data do teste",
                            enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context)
                                        .accentColor
                                        .withOpacity(0.2))),
                            focusedBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context).accentColor)),
                            prefixIcon: Icon(Icons.calendar_today_rounded,
                                color: Theme.of(context).accentColor),
                          ),
                          onTap: () async {
                            FocusScope.of(context).requestFocus(new FocusNode());
                            DateTime? date = DateTime(1900);
                            date = await showDatePicker(
                              context: context, 
                              initialDate:DateTime.now(),
                              firstDate:DateTime(1900),
                              lastDate: DateTime(2100)
                            );
                            birthDateController.text = "${date?.year}-${date?.month}-${date?.day}";
                          },
                        ),
                      // SCHOOLING
                      SizedBox(height: 20),
                      new DropdownButtonFormField<String>(
                        decoration: new InputDecoration(
                          labelText: "Escolha a escolaridade",
                          enabledBorder: UnderlineInputBorder(
                              borderSide: BorderSide(
                                  color: Theme.of(context)
                                      .accentColor
                                      .withOpacity(0.2))),
                          focusedBorder: UnderlineInputBorder(
                              borderSide: BorderSide(
                                  color: Theme.of(context).accentColor)),
                          prefixIcon: Icon(Icons.school,
                              color: Theme.of(context).accentColor),
                        ),
                        value: _currentSchooling,
                        items: schooling.map((schooling) {
                          return DropdownMenuItem(
                            value: schooling,
                            child: Text('$schooling'),
                          );
                        }).toList(),
                        onChanged: (val) {
                          setState(() {
                            _currentSchooling = val;
                          });
                        } ,
                      ),

                      SizedBox(height: 20),
                      new TextButton(
                        style: TextButton.styleFrom(
                          backgroundColor: Colors.orangeAccent,
                          // shape: StadiumBorder(),
                          padding: EdgeInsets.symmetric(
                              vertical: 12, horizontal: 68),
                        ),
                        onPressed: _getImage,
                        child: Row(
                          mainAxisSize: MainAxisSize.max,
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Icon(Icons.camera_alt, color: Colors.white),
                            Text("Selecione a foto", style: TextStyle(color: Colors.white),)
                          ],
                        )
                      ),

                    // SAVE BUTTON
                      SizedBox(height: 30),
                      TextButton(
                        style: TextButton.styleFrom(
                          backgroundColor: Theme.of(context).accentColor,
                          shape: StadiumBorder(),
                          padding: EdgeInsets.symmetric(
                              vertical: 12, horizontal: 68),
                        ),
                        onPressed: ()  {
                          if (globalFormKey.currentState!.validate()) {
                            register();
                            Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => MainMenu()));
                          }
                          
                        },
                        child: Text("Salvar",
                            style: TextStyle(color: Colors.white)),
                      ),
                    ])))
          ])
        ])));
  }

}
