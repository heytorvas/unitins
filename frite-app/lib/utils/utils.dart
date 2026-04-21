import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frite/pages/main_menu.dart';

formValidator(value) {
  if (value == null || value.isEmpty) {
    return 'Insira algum valor';
  }
  return null;
}

appBar(BuildContext context) {
  return AppBar(
        backgroundColor: Theme.of(context).accentColor,
        leading: TextButton(
          child: Icon(Icons.arrow_back_outlined, color: Theme.of(context).primaryColor,),
          onPressed: () {
            Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) =>
                    MainMenu()));
          },
        ),
        title: Text("FRITE", style: TextStyle(
          color: Theme.of(context).primaryColor
        ),),
      );
}