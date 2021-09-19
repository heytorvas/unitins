import 'package:dio/dio.dart';
import 'package:frite/models/psychologist.dart';
import 'package:frite/utils/const.dart';

class RegisterPsychologistService {
  var dio = Dio();

  Future<int?> registerPsychologist(Psychologist psychologist) async {
    try {
      print(psychologist.toJson());
      var response =
          await dio.post("$API/signup/", data: psychologist.toJson());
      print(response);
      if (response.statusCode == 201) {
        return response.statusCode;
      } else {
        return response.statusCode;
      }
    } catch (e) {
      print(e);
      return 0;
    }
  }
}
