import 'package:flutter/material.dart';
import 'package:frite/pages/register_psychologist_page.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({ Key? key }) : super(key: key);

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final scaffoldKey = GlobalKey<ScaffoldState>();
  GlobalKey<FormState> globalFormKey = new GlobalKey<FormState>();
  bool hidePassword = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: scaffoldKey,
      backgroundColor: Theme.of(context).accentColor,
      body: SingleChildScrollView(
        child: Column(
          children: <Widget>[
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
                        )
                      ),
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
                        blurRadius: 20
                      )
                    ]
                  ),
                  child: Form(
                    key: globalFormKey,
                    child: Column(
                      children: <Widget>[
                        SizedBox(height: 25),
                        Text("Login", style: Theme.of(context).textTheme.headline2),
                        SizedBox(height: 20),
                        new TextFormField(
                          keyboardType: TextInputType.emailAddress,
                          //validator: (input)=> !input.contains("@") ? "Email should be valid" : null,
                          decoration: new InputDecoration(
                            hintText: "Insira seu email", 
                            enabledBorder: UnderlineInputBorder(borderSide: BorderSide(color: Theme.of(context).accentColor.withOpacity(0.2))),
                            focusedBorder: UnderlineInputBorder(borderSide: BorderSide(color: Theme.of(context).accentColor)),
                            prefixIcon: Icon(
                              Icons.email, 
                              color: Theme.of(context).accentColor
                            ),
                          ),
                        ),
                        SizedBox(height: 20),
                        new TextFormField(
                          keyboardType: TextInputType.text,
                          // validator: (input)=> input.length < 3 ? "Password invalid" : null,
                          obscureText: hidePassword,
                          decoration: new InputDecoration(
                            hintText: "Insira sua senha", 
                            enabledBorder: UnderlineInputBorder(borderSide: BorderSide(color: Theme.of(context).accentColor.withOpacity(0.2))),
                            focusedBorder: UnderlineInputBorder(borderSide: BorderSide(color: Theme.of(context).accentColor)),
                            prefixIcon: Icon(
                              Icons.password, 
                              color: Theme.of(context).accentColor
                            ),
                            suffixIcon: IconButton(
                              onPressed: (){
                                setState(() {
                                  hidePassword = !hidePassword;
                                });
                              }, 
                              color: Theme.of(context).accentColor.withOpacity(0.4),
                              icon: Icon(hidePassword ? Icons.visibility_off : Icons.visibility),
                            ),
                          ),
                        ),
                        SizedBox(height: 30),
                        TextButton(
                          style: TextButton.styleFrom(
                            backgroundColor: Theme.of(context).accentColor,
                            shape: StadiumBorder(),
                            padding: EdgeInsets.symmetric(vertical: 12, horizontal: 80),
                          ),
                          onPressed: () {}, 
                          child: Text("Entrar", style: TextStyle(color: Colors.white)),
                          
                        ),
                        SizedBox(height: 30),
                        TextButton(
                          style: TextButton.styleFrom(
                            backgroundColor: Theme.of(context).accentColor,
                            shape: StadiumBorder(),
                            padding: EdgeInsets.symmetric(vertical: 12, horizontal: 68),
                          ),
                          onPressed: () {
                            Navigator.push(context, MaterialPageRoute(builder: (context) => RegisterPsychologist()));
                          }, 
                          child: Text("Cadastrar", style: TextStyle(color: Colors.white)),
                        ),
                      ],
                    )
                  ),
                )
              ],
            )
          ]
      ),),
    );
  }
}