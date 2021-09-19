import 'package:flutter/material.dart';
import 'package:frite/models/login.dart';
import 'package:frite/pages/main_menu.dart';
import 'package:frite/pages/register_psychologist_page.dart';
import 'package:frite/services/login_service.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController emailController = new TextEditingController();
  TextEditingController passwordController = new TextEditingController();

  final scaffoldKey = GlobalKey<ScaffoldState>();
  GlobalKey<FormState> globalFormKey = new GlobalKey<FormState>();
  bool hidePassword = true;

  formValidator(value) {
    if (value == null || value.isEmpty) {
      return 'Insira algum valor';
    }
    return null;
  }

  apiValidator(int statusCode) {
    print(statusCode);
    if (statusCode == 200 || statusCode == 201) {
      print('caiu verdade');
      return true;
    }
    else {
      print('caiu falso');
      return false;
    }
  }

  login(BuildContext context) async {
    var service = LoginService();
    var model =
        Login(email: emailController.text, password: passwordController.text);
    print(model);
    var response = await service.login(model);
    var validate = apiValidator(response.statusCode);
    
    if (validate == false){
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Email e/ou senha incorretos.')),
      );
    }
    else {
      Navigator.push(
          context, MaterialPageRoute(builder: (context) => MainMenu()));
    }
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
                        Text("Login",
                            style: Theme.of(context).textTheme.headline2),
                        SizedBox(height: 20),
                        new TextFormField(
                          validator: (value) => formValidator(value),
                          controller: emailController,
                          keyboardType: TextInputType.emailAddress,
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
                        SizedBox(height: 20),
                        new TextFormField(
                          controller: passwordController,
                          keyboardType: TextInputType.text,
                          validator: (value) => formValidator(value),
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
                        SizedBox(height: 30),
                        TextButton(
                          style: TextButton.styleFrom(
                            backgroundColor: Theme.of(context).accentColor,
                            shape: StadiumBorder(),
                            padding: EdgeInsets.symmetric(
                                vertical: 12, horizontal: 80),
                          ),
                          onPressed: () {
                            if (globalFormKey.currentState!.validate()) {
                              login(context);
                            }
                          },
                          child: Text("Entrar",
                              style: TextStyle(color: Colors.white)),
                        ),
                        SizedBox(height: 30),
                        TextButton(
                          style: TextButton.styleFrom(
                            backgroundColor: Theme.of(context).accentColor,
                            shape: StadiumBorder(),
                            padding: EdgeInsets.symmetric(
                                vertical: 12, horizontal: 68),
                          ),
                          onPressed: () {
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) =>
                                        RegisterPsychologist()));
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
