import 'package:dio/dio.dart';
import 'package:frite/main.dart';
import 'package:frite/models/examining.dart';
import 'package:frite/models/palographic.dart';
import 'package:frite/models/psychologist.dart';
import 'package:frite/utils/const.dart';
import 'package:image_picker/image_picker.dart';

class PalographicService {
  static findAll() async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    print(token);
    dio.options.headers['Authorization'] = 'Bearer $token';
    var response = await dio.get("$API/palographic/");

    List<Palographic> lists = [];
    for (var item in response.data) {
      lists.add(Palographic.fromJson(item));
    }
    
    return lists;
  }

  static createPalographic(XFile file, Examining examining, Psychologist psychologist, var testDate, var schooling) async {
    Dio dio = Dio();
    var token = await MyApp.storage.read(key: 'jwt');
    dio.options.headers['Authorization'] = 'Bearer $token';
    String fileName = file.path.split('/').last;
    print(fileName);

    FormData formData = FormData.fromMap({
      "test": await MultipartFile.fromFile(file.path, filename: fileName),
      "examining": examining.id,
      "psychologist": psychologist.id,
      "test_date": testDate,
      "schooling": schooling
    });

    print(formData);

    var response = await dio.post("$API/palographic/", data: formData);
    print(response);

    return response.statusCode;
  }

}