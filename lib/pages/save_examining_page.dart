import 'package:flutter/material.dart';
import 'package:frite/models/examining.dart';
import 'package:frite/pages/examining_list.dart';
import 'package:frite/pages/main_menu.dart';
import 'package:frite/services/examining_service.dart';
import 'package:frite/utils/utils.dart';

class RegisterExamining extends StatefulWidget {
  const RegisterExamining({ Key? key }) : super(key: key);

  @override
  _RegisterExaminingState createState() => _RegisterExaminingState();
}

class _RegisterExaminingState extends State<RegisterExamining> {
  final scaffoldKey = GlobalKey<ScaffoldState>();
  GlobalKey<FormState> globalFormKey = new GlobalKey<FormState>();

  final genders = ['Feminino', 'Masculino'];
  String? _currentGender = 'Feminino';

  final schooling = ['Fundamental', 'Ensino Médio', 'Graduação', 'Mestrado', 'Doutorado', 'Pós-Doutorado'];
  String? _currentSchooling = 'Fundamental';

  TextEditingController nameController = new TextEditingController();
  TextEditingController cpfController = new TextEditingController();
  TextEditingController birthDateController = new TextEditingController();
  TextEditingController cityController = new TextEditingController();
  TextEditingController stateController = new TextEditingController();

  register() {
    var service = ExaminingService();
    var model = Examining(
        name: nameController.text,
        cpf: cpfController.text,
        birthDate: birthDateController.text,
        city: cityController.text,
        state: stateController.text,
        gender: _currentGender,
        schooling: _currentSchooling
      );
    print(model);
    var token = service.registerExamining(model);
  }


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
                    child: Column(
                      children: <Widget>[
                        SizedBox(height: 25),
                        Text("Examinado",
                            style: Theme.of(context).textTheme.headline2),

                        // NAME
                        SizedBox(height: 20),
                        new TextFormField(
                            validator: (value) => formValidator(value),
                            controller: nameController,
                            keyboardType: TextInputType.name,
                            decoration: new InputDecoration(
                              hintText: "Insira o nome",
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
                            )),

                        // CPF
                        SizedBox(height: 20),
                        new TextFormField(
                            validator: (value) => formValidator(value),
                            controller: cpfController,
                            keyboardType: TextInputType.name,
                            decoration: new InputDecoration(
                              hintText: "Insira o CPF",
                              enabledBorder: UnderlineInputBorder(
                                  borderSide: BorderSide(
                                      color: Theme.of(context)
                                          .accentColor
                                          .withOpacity(0.2))),
                              focusedBorder: UnderlineInputBorder(
                                  borderSide: BorderSide(
                                      color: Theme.of(context).accentColor)),
                              prefixIcon: Icon(
                                  Icons.chrome_reader_mode_outlined,
                                  color: Theme.of(context).accentColor),
                            )),

                        // BIRTH DATE
                        SizedBox(height: 20),
                        new TextFormField(
                          controller: birthDateController,
                          keyboardType: TextInputType.datetime,
                          decoration: new InputDecoration(
                            hintText: "Insira a data de nascimento",
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

                        // CITY
                        SizedBox(height: 20),
                        new TextFormField(
                          validator: (value) => formValidator(value),
                          controller: cityController,
                          keyboardType: TextInputType.text,
                          decoration: new InputDecoration(
                            hintText: "Insira a cidade",
                            enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context)
                                        .accentColor
                                        .withOpacity(0.2))),
                            focusedBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context).accentColor)),
                            prefixIcon: Icon(Icons.location_city,
                                color: Theme.of(context).accentColor),
                          )),

                        // STATE
                        SizedBox(height: 20),
                        new TextFormField(
                          validator: (value) => formValidator(value),
                          controller: stateController,
                          keyboardType: TextInputType.text,
                          decoration: new InputDecoration(
                            hintText: "Insira o estado",
                            enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context)
                                        .accentColor
                                        .withOpacity(0.2))),
                            focusedBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context).accentColor)),
                            prefixIcon: Icon(Icons.map,
                                color: Theme.of(context).accentColor),
                          )),

                        // GENDER
                        SizedBox(height: 20),
                        new DropdownButtonFormField<String>(
                          decoration: new InputDecoration(
                            enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context)
                                        .accentColor
                                        .withOpacity(0.2))),
                            focusedBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context).accentColor)),
                            prefixIcon: Icon(Icons.people,
                                color: Theme.of(context).accentColor),
                          ),
                          value: _currentGender,
                          items: genders.map((gender) {
                            return DropdownMenuItem(
                              value: gender,
                              child: Text('$gender'),
                            );
                          }).toList(),
                          onChanged: (val) {
                            setState(() {
                              _currentGender = val;
                            });
                          } ,
                        ),


                        // SCHOOLING
                        SizedBox(height: 20),
                        new DropdownButtonFormField<String>(
                          decoration: new InputDecoration(
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

                        // SAVE BUTTON
                        SizedBox(height: 30),
                        TextButton(
                          style: TextButton.styleFrom(
                            backgroundColor: Theme.of(context).accentColor,
                            shape: StadiumBorder(),
                            padding: EdgeInsets.symmetric(
                                vertical: 12, horizontal: 68),
                          ),
                          onPressed: ()  async {
                            if (globalFormKey.currentState!.validate()) {
                              await register();
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => ExaminingList()));
                            }
                          },
                          child: Text("Salvar",
                              style: TextStyle(color: Colors.white)),
                        ),
                      ],
                    )),
              )
            ],
          )
        ]),
      ),
    );
  }
}