package softuni.medident.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.medident.data.models.Gender;
import softuni.medident.service.models.PatientRegisterServiceModel;
import softuni.medident.web.models.PatientRegisterModel;

@Configuration
public class ModelMapperConfig {

    private static ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        initMapper(mapper);
    }

    private static void initMapper(ModelMapper mapper) {
        Converter<String, Gender> stringGenderConverter = ctx -> Gender.valueOf(ctx.getSource().toUpperCase());

        mapper.createTypeMap(PatientRegisterModel.class, PatientRegisterServiceModel.class)
                .addMappings(map -> map.using(stringGenderConverter)
                        .map(PatientRegisterModel::getGender,
                                PatientRegisterServiceModel::setGender));
    }

    @Bean
    public ModelMapper modelMapper(){
        return mapper;
    }
}
