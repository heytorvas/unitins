import 'package:flutter/material.dart';
import 'package:frite/main.dart';
import 'package:frite/models/psychologist.dart';
import 'package:frite/pages/login_page.dart';
import 'package:frite/pages/main_menu.dart';
import 'package:frite/services/register_psychologist.dart';

class RegisterPsychologist extends StatefulWidget {
  const RegisterPsychologist({Key? key}) : super(key: key);

  @override
  _RegisterPsychologistState createState() => _RegisterPsychologistState();
}

class _RegisterPsychologistState extends State<RegisterPsychologist> {
  var page;

  final scaffoldKey = GlobalKey<ScaffoldState>();
  GlobalKey<FormState> globalFormKey = new GlobalKey<FormState>();
  bool hidePassword = true;

  TextEditingController nameController = new TextEditingController();
  TextEditingController cpfController = new TextEditingController();
  TextEditingController birthDateController = new TextEditingController();
  TextEditingController emailController = new TextEditingController();
  TextEditingController passwordController = new TextEditingController();

  register() {
    var service = RegisterPsychologistService();
    var model = Psychologist(
        name: nameController.text,
        cpf: cpfController.text,
        birthDate: birthDateController.text,
        email: emailController.text,
        password: passwordController.text);
    print(model);
    var token = service.registerPsychologist(model);
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
                        Text("Cadastro do Psicólogo",
                            style: Theme.of(context).textTheme.headline2),

                        // NAME
                        SizedBox(height: 20),
                        new TextFormField(
                            controller: nameController,
                            keyboardType: TextInputType.name,
                            decoration: new InputDecoration(
                              hintText: "Insira seu nome",
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
                              hintText: "Insira seu CPF",
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
                              hintText: "Insira sua data de nascimento",
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
                            )),

                        // EMAIL
                        SizedBox(height: 20),
                        new TextFormField(
                          controller: emailController,
                          keyboardType: TextInputType.emailAddress,
                          //validator: (input)=> !input.contains("@") ? "Email should be valid" : null,
                          decoration: new InputDecoration(
                            hintText: "Insira seu email",
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

                        // PASSWORD
                        SizedBox(height: 20),
                        new TextFormField(
                          controller: passwordController,
                          keyboardType: TextInputType.text,
                          // validator: (input)=> input.length < 3 ? "Password invalid" : null,
                          obscureText: hidePassword,
                          decoration: new InputDecoration(
                            hintText: "Insira sua senha",
                            enabledBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context)
                                        .accentColor
                                        .withOpacity(0.2))),
                            focusedBorder: UnderlineInputBorder(
                                borderSide: BorderSide(
                                    color: Theme.of(context).accentColor)),
                            prefixIcon: Icon(Icons.password,
                                color: Theme.of(context).accentColor),
                            suffixIcon: IconButton(
                              onPressed: () {
                                setState(() {
                                  hidePassword = !hidePassword;
                                });
                              },
                              color: Theme.of(context)
                                  .accentColor
                                  .withOpacity(0.4),
                              icon: Icon(hidePassword
                                  ? Icons.visibility_off
                                  : Icons.visibility),
                            ),
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
                          onPressed: ()  {
                            register();
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => MainMenu()));
                          },
                          child: Text("Cadastrar",
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
