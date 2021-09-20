import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:frite/main.dart';
import 'package:frite/models/psychologist.dart';
import 'package:frite/utils/const.dart';

class PsychologistService {
  
  static findAll() async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    print(token);
    dio.options.headers['Authorization'] = 'Bearer $token';
    var response = await dio.get("$API/psychologist/");

    List<Psychologist> lists = [];
    for (var item in response.data) {
      lists.add(Psychologist.fromJson(item));
    }
    
    return lists;
  }
}
