
import 'package:frite/pages/login_page.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'FRITE',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        fontFamily: 'Poppins',
        primaryColor: Colors.white,
        floatingActionButtonTheme: FloatingActionButtonThemeData(elevation: 0, foregroundColor: Colors.white),
        accentColor: Colors.redAccent,
        textTheme: TextTheme(
          headline1: TextStyle(fontSize: 22.0, color: Colors.redAccent),
          headline2: TextStyle(fontSize: 24.0, fontWeight: FontWeight.w700, color: Colors.redAccent),
          bodyText1: TextStyle(fontSize: 14.0, fontWeight: FontWeight.w400, color: Colors.redAccent)
        ),
      ),
      // home: MyHomePage(title: 'Flutter Demo Home Page'),
      home: LoginPage(),
    );
  }
}
