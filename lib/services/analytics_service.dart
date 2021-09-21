import 'package:dio/dio.dart';
import 'package:frite/main.dart';
import 'package:frite/models/analytics.dart';
import 'package:frite/utils/const.dart';

class AnalyticsService {
  static findAll() async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    print(token);
    dio.options.headers['Authorization'] = 'Bearer $token';
    var response = await dio.get("$API/analysis/");

    print(response);

    List<Analytics> lists = [];
    for (var item in response.data) {
      lists.add(Analytics.fromJson(item));
    }
    
    return lists;
  }
}