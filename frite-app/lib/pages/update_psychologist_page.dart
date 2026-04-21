import 'package:flutter/material.dart';
import 'package:frite/models/psychologist.dart';
import 'package:frite/pages/psychologist_list.dart';
import 'package:frite/services/psychologist_service.dart';
import 'package:frite/utils/utils.dart';

class UpdatePsychologist extends StatefulWidget {
  final Psychologist psychologist;
  const UpdatePsychologist({Key? key, required this.psychologist})
      : super(key: key);

  @override
  _UpdatePsychologistState createState() => _UpdatePsychologistState();
}

class _UpdatePsychologistState extends State<UpdatePsychologist> {
  final scaffoldKey = GlobalKey<ScaffoldState>();
  GlobalKey<FormState> globalFormKey = new GlobalKey<FormState>();
  bool hidePassword = true;

  TextEditingController nameController = new TextEditingController();
  TextEditingController cpfController = new TextEditingController();
  TextEditingController birthDateController = new TextEditingController();
  TextEditingController emailController = new TextEditingController();

  update() async {
    var service = PsychologistService();
    var model = Psychologist(
      name: nameController.text,
      cpf: cpfController.text,
      birthDate: birthDateController.text,
      email: emailController.text,
    );
    print(model);
    var token = await service.update(widget.psychologist.id!, model);
    print(token);
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => PsychologistList()));
  }

  @override
  void initState() {
    super.initState();
    nameController.text = widget.psychologist.name!;
    cpfController.text = widget.psychologist.cpf!;
    birthDateController.text = widget.psychologist.birthDate!;
    emailController.text = widget.psychologist.email!;
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
                  child: Text("Psicólogo",
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
                        Text("Psicólogo",
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
                            FocusScope.of(context)
                                .requestFocus(new FocusNode());
                            DateTime? date = DateTime(1900);
                            date = await showDatePicker(
                                context: context,
                                initialDate: DateTime.now(),
                                firstDate: DateTime(1900),
                                lastDate: DateTime(2100));
                            if (date == null) {
                              date = DateTime.now();
                              birthDateController.text =
                                  "${date.year}-${date.month}-${date.day}";
                            } else {
                              birthDateController.text =
                                  "${date.year}-${date.month}-${date.day}";
                            }
                          },
                        ),

                        // EMAIL
                        SizedBox(height: 20),
                        new TextFormField(
                          controller: emailController,
                          keyboardType: TextInputType.emailAddress,
                          //validator: (input)=> !input.contains("@") ? "Email should be valid" : null,
                          decoration: new InputDecoration(
                            hintText: "Insira o email",
                            enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context)
                                        .accentColor
                                        .withOpacity(0.2))),
                            focusedBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context).accentColor)),
                            prefixIcon: Icon(Icons.email,
                                color: Theme.of(context).accentColor),
                          ),
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
                          onPressed: () {
                            if (globalFormKey.currentState!.validate()) {
                              update();
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
