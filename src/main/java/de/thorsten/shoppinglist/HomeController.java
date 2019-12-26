/*
 * Copyright 2016 codecentric AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.thorsten.shoppinglist;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private RecordRepository repository;

    @Autowired
    private FoodRepository foodRepository;

    private List<Food> allFood;
    private List<Food> suggestedFood;

    @Autowired
    public HomeController(RecordRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home(ModelMap model) {
        System.out.println("/ called ");
        List<Record> records = repository.findAll();
        model.addAttribute("records", records);
        model.addAttribute("insertRecord", new Record());
        List<String> suggestions = new ArrayList<String>();
        return "home";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String insertData(ModelMap model, 
                             @ModelAttribute("insertRecord") @Valid Record record,
                             BindingResult result) {
        if (!result.hasErrors()) {
            repository.save(record);
        }
        return home(model);
    }

    @RequestMapping(value ="/suggestionsAutocomplete", method = RequestMethod.GET)
    @ResponseBody
    public List<String> suggestionsAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term) {
        List<String> suggestions = new ArrayList<String>();
        allFood = foodRepository.findAll();
        suggestedFood = foodRepository.findByNameIsContaining(term);
        System.out.println("Size Food" + suggestedFood.size());

        for (Food food : suggestedFood) {
            suggestions.add(food.getName());
        }
        return suggestions;
    }


}
