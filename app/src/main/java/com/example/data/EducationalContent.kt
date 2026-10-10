package com.example.data

object EducationalContent {

    // 10 Core Daily Routines
    val routineCards: List<RoutineCard> = listOf(
        RoutineCard(
            id = "wake_up",
            expression = "wake up",
            spanishMeaning = "despertarse / levantarse",
            exampleEn = "I wake up at 6:00 AM every school day.",
            exampleEs = "Me despierto a las 6:00 AM todos los días de colegio.",
            timeCategory = RoutineTimeCategory.MORNING,
            iconEmoji = "⏰"
        ),
        RoutineCard(
            id = "take_a_shower",
            expression = "take a shower",
            spanishMeaning = "tomar una ducha / bañarse",
            exampleEn = "I always take a shower before breakfast.",
            exampleEs = "Siempre tomo una ducha antes del desayuno.",
            timeCategory = RoutineTimeCategory.MORNING,
            iconEmoji = "🚿"
        ),
        RoutineCard(
            id = "have_breakfast",
            expression = "have breakfast",
            spanishMeaning = "desayunar",
            exampleEn = "She usually has breakfast with her family.",
            exampleEs = "Ella usualmente desayuna con su familia.",
            timeCategory = RoutineTimeCategory.MORNING,
            iconEmoji = "🍳"
        ),
        RoutineCard(
            id = "go_to_school",
            expression = "go to school",
            spanishMeaning = "ir al colegio / escuela",
            exampleEn = "We go to school by bus at 6:45 AM.",
            exampleEs = "Vamos al colegio en autobús a las 6:45 AM.",
            timeCategory = RoutineTimeCategory.MORNING,
            iconEmoji = "🎒"
        ),
        RoutineCard(
            id = "study",
            expression = "study",
            spanishMeaning = "estudiar",
            exampleEn = "Carlos studies English and science in the morning.",
            exampleEs = "Carlos estudia inglés y ciencias en la mañana.",
            timeCategory = RoutineTimeCategory.MORNING,
            iconEmoji = "📚"
        ),
        RoutineCard(
            id = "have_lunch",
            expression = "have lunch",
            spanishMeaning = "almorzar",
            exampleEn = "Students have lunch in the school cafeteria at 12:30 PM.",
            exampleEs = "Los estudiantes almuerzan en la cafetería escolar a las 12:30 PM.",
            timeCategory = RoutineTimeCategory.AFTERNOON,
            iconEmoji = "🥪"
        ),
        RoutineCard(
            id = "do_homework",
            expression = "do homework",
            spanishMeaning = "hacer la tarea",
            exampleEn = "I often do my homework at 4:00 PM.",
            exampleEs = "A menudo hago mi tarea a las 4:00 PM.",
            timeCategory = RoutineTimeCategory.AFTERNOON,
            iconEmoji = "✍️"
        ),
        RoutineCard(
            id = "play",
            expression = "play",
            spanishMeaning = "jugar / practicar deporte",
            exampleEn = "They sometimes play soccer in the park after school.",
            exampleEs = "Ellos a veces juegan fútbol en el parque después del colegio.",
            timeCategory = RoutineTimeCategory.AFTERNOON,
            iconEmoji = "⚽"
        ),
        RoutineCard(
            id = "have_dinner",
            expression = "have dinner",
            spanishMeaning = "cenar",
            exampleEn = "My family and I have dinner at 7:30 PM.",
            exampleEs = "Mi familia y yo cenamos a las 7:30 PM.",
            timeCategory = RoutineTimeCategory.EVENING,
            iconEmoji = "🍽️"
        ),
        RoutineCard(
            id = "go_to_bed",
            expression = "go to bed",
            spanishMeaning = "irse a la cama / acostarse",
            exampleEn = "He always goes to bed at 9:30 PM to rest well.",
            exampleEs = "Él siempre se acuesta a las 9:30 PM para descansar bien.",
            timeCategory = RoutineTimeCategory.EVENING,
            iconEmoji = "🌙"
        )
    )

    // MISSION ZERO: 15 Diagnostic Questions (5 Vocab, 5 Grammar, 5 Frequency)
    val diagnosticQuestions: List<DiagnosticQuestion> = listOf(
        // Vocabulary (1-5)
        DiagnosticQuestion(
            id = 1,
            category = QuestionCategory.VOCABULARY,
            promptEn = "What action do you do first when your alarm rings in the morning?",
            promptEs = "¿Qué acción realizas primero cuando suena tu alarma en la mañana?",
            optionsEn = listOf("take a shower", "wake up", "do homework", "have dinner"),
            optionsEs = listOf("tomar una ducha", "despertarse", "hacer tarea", "cenar"),
            correctIndex = 1,
            explanationEn = "Correct! 'Wake up' means opening your eyes and getting up in the morning.",
            explanationEs = "¡Correcto! 'Wake up' significa despertarse y levantarse por la mañana."
        ),
        DiagnosticQuestion(
            id = 2,
            category = QuestionCategory.VOCABULARY,
            promptEn = "Which expression means eating the morning meal before school?",
            promptEs = "¿Cuál expresión significa comer la comida matutina antes del colegio?",
            optionsEn = listOf("have lunch", "have dinner", "have breakfast", "go to bed"),
            optionsEs = listOf("almorzar", "cenar", "desayunar", "acostarse"),
            correctIndex = 2,
            explanationEn = "Excellent! 'Have breakfast' is the first meal of the day.",
            explanationEs = "¡Excelente! 'Have breakfast' es la primera comida del día (el desayuno)."
        ),
        DiagnosticQuestion(
            id = 3,
            category = QuestionCategory.VOCABULARY,
            promptEn = "After classes finish, students write and solve tasks at home. What is this?",
            promptEs = "Después de clases, los estudiantes escriben y resuelven tareas en casa. ¿Qué es esto?",
            optionsEn = listOf("do homework", "play video games", "take a shower", "wake up"),
            optionsEs = listOf("hacer la tarea", "jugar videojuegos", "tomar una ducha", "despertarse"),
            correctIndex = 0,
            explanationEn = "Good job! 'Do homework' is completing school assignments at home.",
            explanationEs = "¡Buen trabajo! 'Do homework' significa hacer las tareas escolares en casa."
        ),
        DiagnosticQuestion(
            id = 4,
            category = QuestionCategory.VOCABULARY,
            promptEn = "Which action happens at the end of the day when you sleep?",
            promptEs = "¿Qué acción ocurre al final del día cuando vas a dormir?",
            optionsEn = listOf("go to school", "go to bed", "study", "have lunch"),
            optionsEs = listOf("ir al colegio", "irse a la cama", "estudiar", "almorzar"),
            correctIndex = 1,
            explanationEn = "Spot on! 'Go to bed' means going to sleep at night.",
            explanationEs = "¡Exacto! 'Go to bed' significa irse a la cama a dormir en la noche."
        ),
        DiagnosticQuestion(
            id = 5,
            category = QuestionCategory.VOCABULARY,
            promptEn = "Choose the activity: 'I use water and soap to get clean.'",
            promptEs = "Elige la actividad: 'Uso agua y jabón para limpiarme.'",
            optionsEn = listOf("take a shower", "play", "have dinner", "study"),
            optionsEs = listOf("tomar una ducha", "jugar", "cenar", "estudiar"),
            correctIndex = 0,
            explanationEn = "Well done! 'Take a shower' refers to bathing with water and soap.",
            explanationEs = "¡Bien hecho! 'Take a shower' se refiere a bañarse o ducharse."
        ),

        // Grammar - Present Simple (6-10)
        DiagnosticQuestion(
            id = 6,
            category = QuestionCategory.GRAMMAR,
            promptEn = "Complete: Maria _______ English every Monday afternoon.",
            promptEs = "Completa: Maria _______ inglés todos los lunes en la tarde.",
            optionsEn = listOf("study", "studies", "studying", "studyed"),
            optionsEs = listOf("study (base)", "studies (-ies)", "studying (-ing)", "studyed (incorrecto)"),
            correctIndex = 1,
            explanationEn = "Rule: In 3rd person singular (Maria = she), verbs ending in consonant + y change 'y' to 'ies' -> studies.",
            explanationEs = "Regla: En 3ª persona del singular (Maria = she), los verbos que terminan en consonante + y cambian a -ies -> studies."
        ),
        DiagnosticQuestion(
            id = 7,
            category = QuestionCategory.GRAMMAR,
            promptEn = "Complete with negative form: Diego _______ soccer on weekdays.",
            promptEs = "Completa con la forma negativa: Diego _______ fútbol los días de semana.",
            optionsEn = listOf("don't play", "doesn't play", "doesn't plays", "no play"),
            optionsEs = listOf("don't play", "doesn't play", "doesn't plays", "no play"),
            correctIndex = 1,
            explanationEn = "Rule: For 3rd person singular (he/she/it), use 'doesn't' + base verb (play, without -s).",
            explanationEs = "Regla: Con he, she o it se usa el auxiliar 'doesn't' y el verbo en su forma base (play, sin -s)."
        ),
        DiagnosticQuestion(
            id = 8,
            category = QuestionCategory.GRAMMAR,
            promptEn = "Which sentence is grammatically correct in the affirmative present simple?",
            promptEs = "¿Cuál oración es gramaticalmente correcta en presente simple afirmativo?",
            optionsEn = listOf(
                "My brother wake up at 7:00 AM.",
                "My brother wakes up at 7:00 AM.",
                "My brother waking up at 7:00 AM.",
                "My brother is wake up at 7:00 AM."
            ),
            optionsEs = listOf(
                "My brother wake up at 7:00 AM.",
                "My brother wakes up at 7:00 AM.",
                "My brother waking up at 7:00 AM.",
                "My brother is wake up at 7:00 AM."
            ),
            correctIndex = 1,
            explanationEn = "'My brother' = he, so we add -s to the affirmative verb: 'wakes up'.",
            explanationEs = "'My brother' equivale a él (he), por lo tanto añadimos -s al verbo afirmativo: 'wakes up'."
        ),
        DiagnosticQuestion(
            id = 9,
            category = QuestionCategory.GRAMMAR,
            promptEn = "Complete the question: _______ you do your homework in the evening?",
            promptEs = "Completa la pregunta: _______ you do your homework in the evening?",
            optionsEn = listOf("Do", "Does", "Is", "Are"),
            optionsEs = listOf("Do", "Does", "Is", "Are"),
            correctIndex = 0,
            explanationEn = "With the pronoun 'you', the auxiliary question word in Present Simple is 'Do'.",
            explanationEs = "Con el pronombre 'you', el verbo auxiliar para preguntas en Presente Simple es 'Do'."
        ),
        DiagnosticQuestion(
            id = 10,
            category = QuestionCategory.GRAMMAR,
            promptEn = "Find the correct spelling for 'watch' with 'He':",
            promptEs = "Encuentra la ortografía correcta para 'watch' con 'He':",
            optionsEn = listOf("watchs", "watches", "watching", "watchies"),
            optionsEs = listOf("watchs", "watches", "watching", "watchies"),
            correctIndex = 1,
            explanationEn = "Verbs ending in -ch, -sh, -ss, -x, or -o add -es in 3rd person singular: watch -> watches.",
            explanationEs = "Los verbos que terminan en -ch, -sh, -ss, -x u -o agregan -es en tercera persona: watch -> watches."
        ),

        // Frequency Adverbs (11-15)
        DiagnosticQuestion(
            id = 11,
            category = QuestionCategory.FREQUENCY,
            promptEn = "Which adverb means an action happens 100% of the time?",
            promptEs = "¿Qué adverbio significa que una acción ocurre el 100% de las veces?",
            optionsEn = listOf("never", "sometimes", "always", "rarely"),
            optionsEs = listOf("never (0%)", "sometimes (50%)", "always (100%)", "rarely (20%)"),
            correctIndex = 2,
            explanationEn = "'Always' represents 100% frequency (all the time).",
            explanationEs = "'Always' representa el 100% de frecuencia (siempre, todo el tiempo)."
        ),
        DiagnosticQuestion(
            id = 12,
            category = QuestionCategory.FREQUENCY,
            promptEn = "Which sentence has the correct word order for the adverb of frequency?",
            promptEs = "¿Cuál oración tiene el orden correcto para el adverbio de frecuencia?",
            optionsEn = listOf(
                "I have breakfast always at 7:00.",
                "I always have breakfast at 7:00.",
                "Always I have breakfast at 7:00.",
                "I have always breakfast at 7:00."
            ),
            optionsEs = listOf(
                "I have breakfast always at 7:00.",
                "I always have breakfast at 7:00.",
                "Always I have breakfast at 7:00.",
                "I have always breakfast at 7:00."
            ),
            correctIndex = 1,
            explanationEn = "Frequency adverbs usually go BEFORE the main verb: Subject + Adverb + Main Verb.",
            explanationEs = "Los adverbios de frecuencia van normalmente ANTES del verbo principal: Sujeto + Adverbio + Verbo."
        ),
        DiagnosticQuestion(
            id = 13,
            category = QuestionCategory.FREQUENCY,
            promptEn = "Which adverb indicates an action happens 0% of the time (never happens)?",
            promptEs = "¿Qué adverbio indica que una acción ocurre el 0% de las veces (nunca sucede)?",
            optionsEn = listOf("rarely", "sometimes", "never", "usually"),
            optionsEs = listOf("rarely (rara vez)", "sometimes (a veces)", "never (nunca)", "usually (usualmente)"),
            correctIndex = 2,
            explanationEn = "'Never' expresses 0% frequency (no occurrence).",
            explanationEs = "'Never' expresa 0% de frecuencia (nunca, en ningún momento)."
        ),
        DiagnosticQuestion(
            id = 14,
            category = QuestionCategory.FREQUENCY,
            promptEn = "Where is the frequency adverb placed when using the verb 'to be' (am/is/are)?",
            promptEs = "¿Dónde se ubica el adverbio de frecuencia cuando se usa el verbo 'to be' (am/is/are)?",
            optionsEn = listOf(
                "AFTER the verb to be (e.g., 'He is always happy')",
                "BEFORE the verb to be (e.g., 'He always is happy')",
                "At the very end of the sentence",
                "At the beginning only"
            ),
            optionsEs = listOf(
                "DESPUÉS del verbo to be (ej.: 'He is always happy')",
                "ANTES del verbo to be (ej.: 'He always is happy')",
                "Al final de la oración",
                "Al inicio solamente"
            ),
            correctIndex = 0,
            explanationEn = "Special rule: Adverbs of frequency go AFTER the verb to be (am/is/are) -> 'He is always on time'.",
            explanationEs = "Regla especial: Los adverbios de frecuencia van DESPUÉS del verbo to be -> 'He is always on time'."
        ),
        DiagnosticQuestion(
            id = 15,
            category = QuestionCategory.FREQUENCY,
            promptEn = "Order by frequency from highest to lowest:",
            promptEs = "Ordena por frecuencia de mayor a menor:",
            optionsEn = listOf(
                "always (100%) > usually (80%) > sometimes (50%) > never (0%)",
                "never (0%) > rarely (20%) > sometimes (50%) > always (100%)",
                "sometimes (50%) > always (100%) > usually (80%) > never (0%)",
                "usually (80%) > always (100%) > rarely (20%) > never (0%)"
            ),
            optionsEs = listOf(
                "always (100%) > usually (80%) > sometimes (50%) > never (0%)",
                "never (0%) > rarely (20%) > sometimes (50%) > always (100%)",
                "sometimes (50%) > always (100%) > usually (80%) > never (0%)",
                "usually (80%) > always (100%) > rarely (20%) > never (0%)"
            ),
            correctIndex = 0,
            explanationEn = "Great! The correct hierarchy is: always (100%), usually (80%), often (70%), sometimes (50%), rarely (20%), never (0%).",
            explanationEs = "¡Genial! La jerarquía correcta es: always (100%), usually (80%), often (70%), sometimes (50%), rarely (20%), never (0%)."
        )
    )

    // MISSION 3: VOCABULARY LAB (3 Progressive Tiers, 15 Challenges)
    val vocabLabItems: List<VocabLabItem> = listOf(
        // Tier 1: Cadet (Basic association & time)
        VocabLabItem(
            id = 101,
            tier = LabTier.TIER_1_CADET,
            type = VocabQuestionType.ASSOCIATION,
            promptEn = "Associate the object: An alarm clock on your nightstand belongs to which routine?",
            promptEs = "Asocia el objeto: ¿Un reloj despertador en tu mesa de noche pertenece a qué rutina?",
            optionsEn = listOf("wake up", "have dinner", "play soccer", "do homework"),
            optionsEs = listOf("wake up (despertarse)", "have dinner (cenar)", "play soccer (jugar fútbol)", "do homework (hacer tarea)"),
            correctIndex = 0,
            explanationEn = "The alarm clock rings to help you wake up in the morning!",
            explanationEs = "¡El despertador suena para ayudarte a despertarte por la mañana!",
            clueEn = "It rings at 6:00 AM.",
            clueEs = "Suena a las 6:00 AM."
        ),
        VocabLabItem(
            id = 102,
            tier = LabTier.TIER_1_CADET,
            type = VocabQuestionType.TIME_CLASSIFICATION,
            promptEn = "When do teenagers usually 'have lunch'?",
            promptEs = "¿Cuándo suelen 'almorzar' los adolescentes?",
            optionsEn = listOf("Morning (6:00 AM)", "Afternoon (12:30 PM)", "Night (10:00 PM)", "Midnight"),
            optionsEs = listOf("Mañana (6:00 AM)", "Tarde (12:30 PM)", "Noche (10:00 PM)", "Medianoche"),
            correctIndex = 1,
            explanationEn = "Lunch is the midday meal, typically eaten around 12:00 or 1:00 PM in the afternoon.",
            explanationEs = "El almuerzo es la comida del mediodía, normalmente se toma entre 12:00 y 1:00 PM en la tarde."
        ),
        VocabLabItem(
            id = 103,
            tier = LabTier.TIER_1_CADET,
            type = VocabQuestionType.ASSOCIATION,
            promptEn = "Associate: Soap, warm water, and a towel connect with:",
            promptEs = "Asocia: El jabón, agua tibia y una toalla conectan con:",
            optionsEn = listOf("take a shower", "study", "go to bed", "have breakfast"),
            optionsEs = listOf("take a shower", "study", "go to bed", "have breakfast"),
            correctIndex = 0,
            explanationEn = "You use soap, water and a towel when taking a shower.",
            explanationEs = "Usas jabón, agua y una toalla al ducharte (take a shower)."
        ),
        VocabLabItem(
            id = 104,
            tier = LabTier.TIER_1_CADET,
            type = VocabQuestionType.TRUE_FALSE,
            promptEn = "True or False: 'Have breakfast' happens at 8:00 PM at night.",
            promptEs = "¿Verdadero o Falso? 'Have breakfast' ocurre a las 8:00 PM en la noche.",
            optionsEn = listOf("False (It happens in the morning)", "True (It happens at night)"),
            optionsEs = listOf("Falso (Ocurre en la mañana)", "Verdadero (Ocurre en la noche)"),
            correctIndex = 0,
            explanationEn = "False! Breakfast is in the morning; dinner is at night.",
            explanationEs = "¡Falso! El desayuno se toma por la mañana; en la noche se cena (dinner)."
        ),
        VocabLabItem(
            id = 105,
            tier = LabTier.TIER_1_CADET,
            type = VocabQuestionType.ASSOCIATION,
            promptEn = "Associate: A pillow, pajamas, and darkness belong to:",
            promptEs = "Asocia: Una almohada, pijamas y oscuridad pertenecen a:",
            optionsEn = listOf("go to bed", "go to school", "play", "have lunch"),
            optionsEs = listOf("go to bed", "go to school", "play", "have lunch"),
            correctIndex = 0,
            explanationEn = "Going to bed involves pajamas, pillows, and sleeping at night.",
            explanationEs = "Ir a la cama (go to bed) involucra pijamas, almohada y descansar."
        ),

        // Tier 2: Navigator (Contextual phrases & categorizations)
        VocabLabItem(
            id = 106,
            tier = LabTier.TIER_2_NAVIGATOR,
            type = VocabQuestionType.ASSOCIATION,
            promptEn = "Which action describes carrying a backpack and boarding the yellow bus?",
            promptEs = "¿Qué acción describe llevar una mochila y abordar el bus amarillo?",
            optionsEn = listOf("go to school", "have dinner", "wake up", "take a shower"),
            optionsEs = listOf("go to school", "have dinner", "wake up", "take a shower"),
            correctIndex = 0,
            explanationEn = "Carrying a backpack and taking the bus is part of 'go to school'.",
            explanationEs = "Llevar la mochila y tomar el bus escolar forma parte de 'go to school'."
        ),
        VocabLabItem(
            id = 107,
            tier = LabTier.TIER_2_NAVIGATOR,
            type = VocabQuestionType.UNSCRAMBLE,
            promptEn = "Select the correct expression for reviewing school materials with a pencil and notebooks:",
            promptEs = "Selecciona la expresión correcta para revisar materiales con lápiz y cuadernos:",
            optionsEn = listOf("do homework", "make dishes", "play soccer", "sleep morning"),
            optionsEs = listOf("do homework (hacer tarea)", "make dishes", "play soccer", "sleep morning"),
            correctIndex = 0,
            explanationEn = "Notice the collocation: in English we say 'DO homework', never 'make homework'.",
            explanationEs = "Recuerda la combinación en inglés: se dice 'DO homework', nunca 'make homework'."
        ),
        VocabLabItem(
            id = 108,
            tier = LabTier.TIER_2_NAVIGATOR,
            type = VocabQuestionType.TIME_CLASSIFICATION,
            promptEn = "Classify: Which of these is an EVENING routine?",
            promptEs = "Clasifica: ¿Cuál de estas es una rutina de la NOCHE?",
            optionsEn = listOf("have dinner", "have breakfast", "go to school", "wake up"),
            optionsEs = listOf("have dinner", "have breakfast", "go to school", "wake up"),
            correctIndex = 0,
            explanationEn = "'Have dinner' takes place in the evening, usually between 7:00 PM and 8:30 PM.",
            explanationEs = "'Have dinner' (cenar) se realiza al anochecer, entre 7:00 y 8:30 PM."
        ),
        VocabLabItem(
            id = 109,
            tier = LabTier.TIER_2_NAVIGATOR,
            type = VocabQuestionType.ASSOCIATION,
            promptEn = "Associate: 'My friends and I kick the ball and score goals.'",
            promptEs = "Asocia: 'Mis amigos y yo pateamos el balón y anotamos goles.'",
            optionsEn = listOf("play", "study", "take a shower", "go to bed"),
            optionsEs = listOf("play (jugar)", "study", "take a shower", "go to bed"),
            correctIndex = 0,
            explanationEn = "The verb is 'play' (play soccer / play sports).",
            explanationEs = "El verbo es 'play' (jugar fútbol / practicar deporte)."
        ),
        VocabLabItem(
            id = 110,
            tier = LabTier.TIER_2_NAVIGATOR,
            type = VocabQuestionType.TRUE_FALSE,
            promptEn = "True or False: 'Study' only means reading English books at home.",
            promptEs = "¿Verdadero o Falso? 'Study' solo significa leer libros de inglés en casa.",
            optionsEn = listOf("False (You study many subjects at school and home)", "True (Only English books)"),
            optionsEs = listOf("Falso (Se estudian múltiples asignaturas en la escuela y hogar)", "Verdadero (Solo libros de inglés)"),
            correctIndex = 0,
            explanationEn = "'Study' applies to all academic subjects: math, science, history, languages, etc.",
            explanationEs = "'Study' aplica a todas las asignaturas escolares: matemáticas, ciencias, etc."
        ),

        // Tier 3: Master (Advanced collocations & multi-routine flow)
        VocabLabItem(
            id = 111,
            tier = LabTier.TIER_3_MASTER,
            type = VocabQuestionType.UNSCRAMBLE,
            promptEn = "Complete the chronological morning sequence: 1. Wake up -> 2. _________ -> 3. Have breakfast",
            promptEs = "Completa la secuencia matutina cronológica: 1. Despertar -> 2. _________ -> 3. Desayunar",
            optionsEn = listOf("take a shower", "go to bed", "have dinner", "do homework"),
            optionsEs = listOf("take a shower", "go to bed", "have dinner", "do homework"),
            correctIndex = 0,
            explanationEn = "A logical morning routine: wake up, take a shower, then have breakfast.",
            explanationEs = "Secuencia lógica: despertarse, ducharse y luego desayunar."
        ),
        VocabLabItem(
            id = 112,
            tier = LabTier.TIER_3_MASTER,
            type = VocabQuestionType.ASSOCIATION,
            promptEn = "Which pair uses the correct English collocations?",
            promptEs = "¿Cuál par utiliza las combinaciones verbales correctas en inglés?",
            optionsEn = listOf(
                "have breakfast / do homework",
                "eat breakfast / make homework",
                "take breakfast / write homework",
                "drink breakfast / play homework"
            ),
            optionsEs = listOf(
                "have breakfast / do homework (Correcto)",
                "eat breakfast / make homework",
                "take breakfast / write homework",
                "drink breakfast / play homework"
            ),
            correctIndex = 0,
            explanationEn = "In English we standardly say 'have breakfast' and 'do homework'.",
            explanationEs = "En inglés las colocaciones naturales son 'have breakfast' y 'do homework'."
        ),
        VocabLabItem(
            id = 113,
            tier = LabTier.TIER_3_MASTER,
            type = VocabQuestionType.TIME_CLASSIFICATION,
            promptEn = "Identify all three afternoon routines:",
            promptEs = "Identifica las tres rutinas de la tarde:",
            optionsEn = listOf(
                "have lunch, do homework, play",
                "wake up, take a shower, go to school",
                "have dinner, go to bed, wake up",
                "go to bed, have breakfast, study"
            ),
            optionsEs = listOf(
                "have lunch, do homework, play",
                "wake up, take a shower, go to school",
                "have dinner, go to bed, wake up",
                "go to bed, have breakfast, study"
            ),
            correctIndex = 0,
            explanationEn = "Afternoon routines include: have lunch (midday), do homework, and play with friends.",
            explanationEs = "Las actividades de la tarde son: almorzar, hacer la tarea y jugar."
        ),
        VocabLabItem(
            id = 114,
            tier = LabTier.TIER_3_MASTER,
            type = VocabQuestionType.TRUE_FALSE,
            promptEn = "True or False: In English, 'have' is commonly used for meals (breakfast, lunch, dinner).",
            promptEs = "¿Verdadero o Falso? En inglés, 'have' se usa comúnmente para las comidas (breakfast, lunch, dinner).",
            optionsEn = listOf("True ('have breakfast, have lunch, have dinner')", "False (Only 'eat' is allowed)"),
            optionsEs = listOf("Verdadero ('have breakfast, have lunch, have dinner')", "Falso (Solo se permite 'eat')"),
            correctIndex = 0,
            explanationEn = "True! English native speakers frequently use 'have' for meals.",
            explanationEs = "¡Verdadero! Los angloparlantes usan comúnmente 'have' para las tres comidas."
        ),
        VocabLabItem(
            id = 115,
            tier = LabTier.TIER_3_MASTER,
            type = VocabQuestionType.ASSOCIATION,
            promptEn = "Match the role: In collaborative pair work, what does the 'Checker' (Verificador) do?",
            promptEs = "Relaciona el rol: En el trabajo por parejas, ¿qué hace el 'Checker' (Verificador)?",
            optionsEn = listOf(
                "Checks spelling, accuracy, and confirms before sending",
                "Only reads the text aloud",
                "Only picks the buttons quickly without looking",
                "Leaves the room"
            ),
            optionsEs = listOf(
                "Verifica la ortografía, precisión y confirma antes de enviar",
                "Solo lee el texto en voz alta",
                "Solo presiona botones sin mirar",
                "Se va del salón"
            ),
            correctIndex = 0,
            explanationEn = "The Checker verifies correctness and supports the team with feedback!",
            explanationEs = "El Verificador revisa la precisión y asegura que el equipo elija la respuesta correcta."
        )
    )

    // MISSION 4: GRAMMAR CHALLENGE (15 Items with Rules & Explanations)
    val grammarChallenges: List<GrammarChallengeItem> = listOf(
        GrammarChallengeItem(
            id = 201,
            type = GrammarChallengeType.SELECT_FORM,
            ruleCategory = "3rd_person",
            promptEn = "Select the correct form: She _______ (take) a shower at 6:30 AM.",
            promptEs = "Selecciona la forma correcta: She _______ (take) a shower at 6:30 AM.",
            optionsEn = listOf("take", "takes", "taking", "takies"),
            optionsEs = listOf("take", "takes", "taking", "takies"),
            correctIndex = 1,
            explanationEn = "For 3rd person singular (he/she/it), add -s to the affirmative verb: take -> takes.",
            explanationEs = "Para la 3ª persona del singular (he/she/it), añade -s al verbo afirmativo: take -> takes.",
            ruleTipEn = "General rule: Subject (he/she/it) + Verb + -s.",
            ruleTipEs = "Regla general: Sujeto (he/she/it) + Verbo + -s."
        ),
        GrammarChallengeItem(
            id = 202,
            type = GrammarChallengeType.SPOT_AND_FIX,
            ruleCategory = "negative",
            promptEn = "Find the error: 'He don't do his homework in the afternoon.' What is the correct phrase?",
            promptEs = "Encuentra el error: 'He don't do his homework in the afternoon.' ¿Cuál es la frase correcta?",
            optionsEn = listOf("He doesn't do", "He not do", "He isn't do", "He doesn't does"),
            optionsEs = listOf("He doesn't do", "He not do", "He isn't do", "He doesn't does"),
            correctIndex = 0,
            explanationEn = "With 'he', the negative auxiliary is 'doesn't'. The main verb stays in base form: 'do' (not does).",
            explanationEs = "Con 'he', el auxiliar negativo es 'doesn't'. El verbo principal permanece en forma base: 'do'.",
            ruleTipEn = "Negative 3rd person: Subject + doesn't + Base Verb.",
            ruleTipEs = "Negación en 3ª persona: Sujeto + doesn't + Verbo en forma base."
        ),
        GrammarChallengeItem(
            id = 203,
            type = GrammarChallengeType.SELECT_FORM,
            ruleCategory = "3rd_person_ies",
            promptEn = "Apply the -ies rule: My friend _______ (study) at the local high school.",
            promptEs = "Aplica la regla de -ies: My friend _______ (study) en el colegio local.",
            optionsEn = listOf("studys", "studies", "study", "is study"),
            optionsEs = listOf("studys", "studies", "study", "is study"),
            correctIndex = 1,
            explanationEn = "Consonant + 'y' rule: study ends in consonant 'd' + 'y'. Change 'y' to 'ies' -> studies.",
            explanationEs = "Regla de consonante + 'y': study termina en consonante 'd' + 'y'. Se cambia la 'y' por 'ies' -> studies.",
            ruleTipEn = "Consonant + y = change to -ies. (Contrast: Vowel + y: play -> plays)",
            ruleTipEs = "Consonante + y = cambia a -ies. (Contraste: Vocal + y: play -> plays)"
        ),
        GrammarChallengeItem(
            id = 204,
            type = GrammarChallengeType.SELECT_FORM,
            ruleCategory = "3rd_person_vowel_y",
            promptEn = "Notice the contrast: Carlos _______ (play) soccer on Sundays.",
            promptEs = "Observa el contraste: Carlos _______ (play) fútbol los domingos.",
            optionsEn = listOf("plaies", "plays", "play", "playing"),
            optionsEs = listOf("plaies", "plays", "play", "playing"),
            correctIndex = 1,
            explanationEn = "In 'play', the 'y' is preceded by vowel 'a', so simply add -s -> plays (never plaies!).",
            explanationEs = "En 'play', la 'y' viene después de la vocal 'a', así que solo se añade -s -> plays (¡nunca plaies!).",
            ruleTipEn = "Vowel + y = just add -s (play -> plays, stay -> stays).",
            ruleTipEs = "Vocal + y = solo se agrega -s (play -> plays, stay -> stays)."
        ),
        GrammarChallengeItem(
            id = 205,
            type = GrammarChallengeType.WORD_ORDER,
            ruleCategory = "frequency_position",
            promptEn = "Unscramble into the correct word order:",
            promptEs = "Ordena las palabras en el orden correcto:",
            optionsEn = listOf(
                "She always eats breakfast at 7:00.",
                "Always she eats breakfast at 7:00.",
                "She eats always breakfast at 7:00.",
                "Breakfast she always eats at 7:00."
            ),
            optionsEs = listOf(
                "She always eats breakfast at 7:00.",
                "Always she eats breakfast at 7:00.",
                "She eats always breakfast at 7:00.",
                "Breakfast she always eats at 7:00."
            ),
            correctIndex = 0,
            explanationEn = "Frequency adverbs (always, usually, often, etc.) go BEFORE regular action verbs.",
            explanationEs = "Los adverbios de frecuencia van ANTES de los verbos de acción regulares: She always eats...",
            ruleTipEn = "Subject + Adverb of Frequency + Main Verb + Complement.",
            ruleTipEs = "Sujeto + Adverbio de frecuencia + Verbo principal + Complemento."
        ),
        GrammarChallengeItem(
            id = 206,
            type = GrammarChallengeType.SELECT_FORM,
            ruleCategory = "3rd_person_es",
            promptEn = "Apply the -es rule for verbs ending in -ch: He _______ (watch) the routine video.",
            promptEs = "Aplica la regla de -es para verbos que terminan en -ch: He _______ (watch) el video.",
            optionsEn = listOf("watchs", "watches", "watching", "is watch"),
            optionsEs = listOf("watchs", "watches", "watching", "is watch"),
            correctIndex = 1,
            explanationEn = "Verbs ending in -ch, -sh, -ss, -x, -o take -es: watch -> watches, go -> goes.",
            explanationEs = "Verbos terminados en -ch, -sh, -ss, -x, -o agregan -es: watch -> watches, go -> goes.",
            ruleTipEn = "Add -es after -ch, -sh, -ss, -x, -o.",
            ruleTipEs = "Agrega -es después de -ch, -sh, -ss, -x, -o."
        ),
        GrammarChallengeItem(
            id = 207,
            type = GrammarChallengeType.SPOT_AND_FIX,
            ruleCategory = "3rd_person",
            promptEn = "Identify the mistake in: 'My sister go to school at 7:00 AM.'",
            promptEs = "Identifica el error en: 'My sister go to school at 7:00 AM.'",
            optionsEn = listOf(
                "Should be 'goes' (My sister goes to school)",
                "Should be 'gos' (My sister gos to school)",
                "Should be 'is go' (My sister is go to school)",
                "No mistake"
            ),
            optionsEs = listOf(
                "Debe ser 'goes' (My sister goes to school)",
                "Debe ser 'gos' (My sister gos to school)",
                "Debe ser 'is go' (My sister is go to school)",
                "Sin error"
            ),
            correctIndex = 0,
            explanationEn = "'Go' ends in 'o', so with 3rd person (my sister = she) it becomes 'goes'.",
            explanationEs = "'Go' termina en vocal 'o', por lo que con tercera persona (my sister = she) se escribe 'goes'.",
            ruleTipEn = "go -> goes, do -> does.",
            ruleTipEs = "go -> goes, do -> does."
        ),
        GrammarChallengeItem(
            id = 208,
            type = GrammarChallengeType.FILL_IN_BLANK,
            ruleCategory = "frequency_be",
            promptEn = "Where does 'never' go with the verb 'is'?",
            promptEs = "¿Dónde va 'never' con el verbo 'is'?",
            optionsEn = listOf(
                "Daniel is never late for class.",
                "Daniel never is late for class.",
                "Never Daniel is late for class.",
                "Daniel is late never for class."
            ),
            optionsEs = listOf(
                "Daniel is never late for class.",
                "Daniel never is late for class.",
                "Never Daniel is late for class.",
                "Daniel is late never for class."
            ),
            correctIndex = 0,
            explanationEn = "With verb TO BE (am/is/are), frequency adverbs go AFTER the verb: 'is never late'.",
            explanationEs = "Con el verbo TO BE (am/is/are), el adverbio va DESPUÉS del verbo: 'is never late'.",
            ruleTipEn = "Subject + BE (am/is/are) + Adverb of Frequency.",
            ruleTipEs = "Sujeto + BE (am/is/are) + Adverbio de frecuencia."
        ),
        GrammarChallengeItem(
            id = 209,
            type = GrammarChallengeType.SELECT_FORM,
            ruleCategory = "questions",
            promptEn = "Form the question: _______ he do his homework in the evening?",
            promptEs = "Forma la pregunta: _______ he do his homework in the evening?",
            optionsEn = listOf("Do", "Does", "Are", "Has"),
            optionsEs = listOf("Do", "Does", "Are", "Has"),
            correctIndex = 1,
            explanationEn = "For 3rd person singular (he/she/it), start questions with auxiliary 'Does'!",
            explanationEs = "Para la 3ª persona del singular (he/she/it), las preguntas comienzan con 'Does'.",
            ruleTipEn = "Does + he/she/it + Base Verb...?",
            ruleTipEs = "Does + he/she/it + Verbo en forma base...?"
        ),
        GrammarChallengeItem(
            id = 210,
            type = GrammarChallengeType.WORD_ORDER,
            ruleCategory = "frequency_position",
            promptEn = "Unscramble: [usually] [they] [play] [soccer] [after school]",
            promptEs = "Ordena: [usually] [they] [play] [soccer] [after school]",
            optionsEn = listOf(
                "They usually play soccer after school.",
                "They play usually soccer after school.",
                "Usually they soccer play after school.",
                "They soccer play usually after school."
            ),
            optionsEs = listOf(
                "They usually play soccer after school.",
                "They play usually soccer after school.",
                "Usually they soccer play after school.",
                "They soccer play usually after school."
            ),
            correctIndex = 0,
            explanationEn = "Subject (They) + Frequency (usually) + Verb (play) + Object + Time.",
            explanationEs = "Sujeto (They) + Adverbio (usually) + Verbo (play) + Objeto + Complemento.",
            ruleTipEn = "They + usually + play soccer.",
            ruleTipEs = "They + usually + play soccer."
        ),
        GrammarChallengeItem(
            id = 211,
            type = GrammarChallengeType.SELECT_FORM,
            ruleCategory = "first_person",
            promptEn = "Notice 1st person 'I': I _______ (wake up) at 6:00 AM.",
            promptEs = "Observa la 1ª persona 'I': I _______ (wake up) at 6:00 AM.",
            optionsEn = listOf("wake up", "wakes up", "waking up", "woke up"),
            optionsEs = listOf("wake up (sin -s)", "wakes up (con -s)", "waking up", "woke up"),
            correctIndex = 0,
            explanationEn = "With 'I', 'you', 'we', 'they', do NOT add -s! Keep the base verb: 'I wake up'.",
            explanationEs = "Con 'I', 'you', 'we', 'they', ¡NO agregues -s! Usa el verbo base: 'I wake up'.",
            ruleTipEn = "Only he, she, it add -s in the affirmative.",
            ruleTipEs = "Solo he, she, it agregan -s en afirmativo."
        ),
        GrammarChallengeItem(
            id = 212,
            type = GrammarChallengeType.SPOT_AND_FIX,
            ruleCategory = "questions",
            promptEn = "Find the error: 'Does she wakes up early?' What is the correction?",
            promptEs = "Encuentra el error: 'Does she wakes up early?' ¿Cuál es la corrección?",
            optionsEn = listOf(
                "Does she wake up early? (remove -s because 'Does' is used)",
                "Do she wakes up early?",
                "Is she wakes up early?",
                "Does she waking up early?"
            ),
            optionsEs = listOf(
                "Does she wake up early? (quitar la -s porque ya está 'Does')",
                "Do she wakes up early?",
                "Is she wakes up early?",
                "Does she waking up early?"
            ),
            correctIndex = 0,
            explanationEn = "Important: When 'Does' is present in a question, the main verb reverts to base form (wake up, NOT wakes up)!",
            explanationEs = "¡Importante! Cuando 'Does' está en la pregunta, el verbo principal vuelve a su forma base (wake up, NO wakes up).",
            ruleTipEn = "Auxiliary 'Does' absorbs the 3rd person marker.",
            ruleTipEs = "El auxiliar 'Does' absorbe la marca de tercera persona."
        ),
        GrammarChallengeItem(
            id = 213,
            type = GrammarChallengeType.SELECT_FORM,
            ruleCategory = "frequency_meaning",
            promptEn = "Which sentence expresses that Lucía studies 70% of days?",
            promptEs = "¿Cuál oración expresa que Lucía estudia el 70% de los días?",
            optionsEn = listOf(
                "Lucía often studies in the library.",
                "Lucía never studies in the library.",
                "Lucía rarely studies in the library.",
                "Lucía always studies in the library."
            ),
            optionsEs = listOf(
                "Lucía often studies in the library. (often = 70%)",
                "Lucía never studies in the library. (never = 0%)",
                "Lucía rarely studies in the library. (rarely = 20%)",
                "Lucía always studies in the library. (always = 100%)"
            ),
            correctIndex = 0,
            explanationEn = "'Often' indicates frequent action, approximately 70% of the time.",
            explanationEs = "'Often' indica acción frecuente (a menudo), aproximadamente el 70% del tiempo.",
            ruleTipEn = "often = frequently (70%).",
            ruleTipEs = "often = con frecuencia / a menudo (70%)."
        ),
        GrammarChallengeItem(
            id = 214,
            type = GrammarChallengeType.FILL_IN_BLANK,
            ruleCategory = "irregular_have",
            promptEn = "Irregular 3rd person: She _______ (have) lunch at 12:30 PM.",
            promptEs = "3ª persona irregular: She _______ (have) lunch at 12:30 PM.",
            optionsEn = listOf("haves", "has", "having", "is have"),
            optionsEs = listOf("haves (incorrecto)", "has (correcto)", "having", "is have"),
            correctIndex = 1,
            explanationEn = "'Have' is irregular in the 3rd person singular: it becomes 'HAS', never 'haves'.",
            explanationEs = "'Have' es irregular en 3ª persona del singular: se convierte en 'HAS', ¡nunca 'haves'!",
            ruleTipEn = "have -> has with he/she/it.",
            ruleTipEs = "have -> has con he/she/it."
        ),
        GrammarChallengeItem(
            id = 215,
            type = GrammarChallengeType.WORD_ORDER,
            ruleCategory = "negative_frequency",
            promptEn = "Complete: We _______ eat junk food on school days.",
            promptEs = "Completa: Nosotros _______ comemos comida chatarra en días de escuela.",
            optionsEn = listOf("rarely", "rare", "at rarely", "no rarely"),
            optionsEs = listOf("rarely (rara vez)", "rare", "at rarely", "no rarely"),
            correctIndex = 0,
            explanationEn = "'Rarely' is the adverb of frequency meaning seldom or almost never (20%).",
            explanationEs = "'Rarely' es el adverbio de frecuencia que significa rara vez (20%).",
            ruleTipEn = "rarely = seldom (20%).",
            ruleTipEs = "rarely = rara vez (20%)."
        )
    )

    // MISSION 5: READING MISSION (3 Teenager Daily Routine Stories with Comprehension & Sequencing)
    val readingStories: List<ReadingStoryItem> = listOf(
        ReadingStoryItem(
            id = "story_leo",
            titleEn = "Leo's Coastal Routine in Cartagena",
            titleEs = "La rutina costera de Leo en Cartagena",
            characterName = "Leo",
            location = "Cartagena, Colombia",
            flag = "🇨🇴",
            textEn = """Hello! My name is Leo and I am 13 years old. I live in the vibrant city of Cartagena, Colombia. 
Every morning, I always wake up at 5:45 AM because the morning breeze is cool. First, I take a quick cold shower to wake up. Then, I have breakfast with my grandmother: we usually eat fresh arepas with cheese and drink hot chocolate.

At 6:30 AM, I walk to my school with my friends Mateo and Valentina. We study math, science and English. At 12:30 PM, we have lunch in the school yard. 

In the afternoon, I always do my homework first. After that, I usually play soccer on the beach at 4:30 PM. I love scoring goals with my team! In the evening, my family and I have dinner at 7:00 PM. Finally, I read a book and go to bed at 9:00 PM. Every day is an exciting adventure!""",
            textEs = """¡Hola! Mi nombre es Leo y tengo 13 años. Vivo en la vibrante ciudad de Cartagena, Colombia.
Cada mañana, siempre me despierto a las 5:45 AM porque la brisa matutina es fresca. Primero, tomo una ducha fría rápida para despertar. Luego, desayuno con mi abuela: usualmente comemos arepas frescas con queso y tomamos chocolate caliente.

A las 6:30 AM, camino a mi colegio con mis amigos Mateo y Valentina. Estudiamos matemáticas, ciencias e inglés. A las 12:30 PM, almorzamos en el patio escolar.

En la tarde, siempre hago mi tarea primero. Después de eso, usualmente juego fútbol en la playa a las 4:30 PM. ¡Me encanta meter goles con mi equipo! En la noche, mi familia y yo cenamos a las 7:00 PM. Finalmente, leo un libro y me voy a la cama a las 9:00 PM. ¡Cada día es una aventura emocionante!""",
            keyVocab = listOf(
                "wake up" to "despertarse",
                "take a shower" to "tomar una ducha",
                "have breakfast" to "desayunar",
                "walk to school" to "caminar al colegio",
                "have lunch" to "almorzar",
                "do homework" to "hacer la tarea",
                "play soccer" to "jugar fútbol",
                "have dinner" to "cenar",
                "go to bed" to "acostarse"
            ),
            questions = listOf(
                DiagnosticQuestion(
                    id = 301,
                    category = QuestionCategory.VOCABULARY,
                    promptEn = "What time does Leo wake up in the morning?",
                    promptEs = "¿A qué hora se despierta Leo por la mañana?",
                    optionsEn = listOf("5:45 AM", "7:00 AM", "6:30 AM", "8:00 AM"),
                    optionsEs = listOf("5:45 AM", "7:00 AM", "6:30 AM", "8:00 AM"),
                    correctIndex = 0,
                    explanationEn = "The text says: 'Every morning, I always wake up at 5:45 AM'.",
                    explanationEs = "El texto indica: 'Every morning, I always wake up at 5:45 AM'."
                ),
                DiagnosticQuestion(
                    id = 302,
                    category = QuestionCategory.FREQUENCY,
                    promptEn = "What does Leo ALWAYS do first in the afternoon before playing soccer?",
                    promptEs = "¿Qué hace Leo SIEMPRE primero en la tarde antes de jugar fútbol?",
                    optionsEn = listOf("He does his homework", "He watches TV", "He takes a nap", "He goes to bed"),
                    optionsEs = listOf("Hace su tarea", "Mira televisión", "Toma una siesta", "Se acuesta"),
                    correctIndex = 0,
                    explanationEn = "Leo states: 'In the afternoon, I always do my homework first.'",
                    explanationEs = "Leo afirma: 'In the afternoon, I always do my homework first.'"
                ),
                DiagnosticQuestion(
                    id = 303,
                    category = QuestionCategory.GRAMMAR,
                    promptEn = "What is the chronological sequence of Leo's morning actions?",
                    promptEs = "¿Cuál es la secuencia cronológica de las acciones matutinas de Leo?",
                    optionsEn = listOf(
                        "Wake up -> Take a shower -> Have breakfast -> Walk to school",
                        "Have breakfast -> Wake up -> Walk to school -> Play",
                        "Walk to school -> Wake up -> Have lunch -> Sleep",
                        "Take a shower -> Walk to school -> Wake up -> Sleep"
                    ),
                    optionsEs = listOf(
                        "Despertar -> Ducharse -> Desayunar -> Caminar al colegio",
                        "Desayunar -> Despertar -> Caminar al colegio -> Jugar",
                        "Caminar al colegio -> Despertar -> Almorzar -> Dormir",
                        "Ducharse -> Caminar al colegio -> Despertar -> Dormir"
                    ),
                    correctIndex = 0,
                    explanationEn = "Chronological order: 1. Wake up (5:45), 2. Shower, 3. Breakfast, 4. Walk to school (6:30).",
                    explanationEs = "Orden cronológico: 1. Despertar, 2. Ducha, 3. Desayuno, 4. Caminar al colegio."
                )
            ),
            imageResName = "reading_leo"
        ),
        ReadingStoryItem(
            id = "story_sofia",
            titleEn = "Sofia's Music Routine in London",
            titleEs = "La rutina musical de Sofía en Londres",
            characterName = "Sofia",
            location = "London, United Kingdom",
            flag = "🇬🇧",
            textEn = """Hi there! I am Sofia, a 12-year-old student from London. 
On weekdays, I usually wake up at 6:45 AM when my digital clock beeps. I take a warm shower and put on my school uniform. At 7:15 AM, I have breakfast: my parents and I always have toast with marmalade and a glass of milk.

At 7:45 AM, I go to school by red double-decker bus. I listen to music during the ride. My classes begin at 8:30 AM. We study English literature, history and science. I often have lunch with my best friend Oliver at 1:00 PM.

School finishes at 3:15 PM. Then, I go to the music academy because I play the violin! I practice for one hour every Tuesday and Thursday. When I get home, I do my homework. At 8:00 PM, we have dinner together. I rarely watch TV late; I always go to bed at 9:30 PM.""",
            textEs = """¡Hola! Soy Sofía, una estudiante de 12 años de Londres.
Entre semana, usualmente me despierto a las 6:45 AM cuando mi reloj digital suena. Tomo una ducha caliente y me pongo mi uniforme escolar. A las 7:15 AM, desayuno: mis padres y yo siempre comemos tostadas con mermelada y un vaso de leche.

A las 7:45 AM, voy al colegio en el autobús rojo de dos pisos. Escucho música durante el trayecto. Mis clases comienzan a las 8:30 AM. Estudiamos literatura inglesa, historia y ciencias. A menudo almuerzo con mi mejor amigo Oliver a la 1:00 PM.

El colegio termina a las 3:15 PM. Luego, voy a la academia de música porque ¡toco el violín! Practico una hora cada martes y jueves. Cuando llego a casa, hago mi tarea. A las 8:00 PM, cenamos juntos. Rara vez veo televisión hasta tarde; siempre me acuesto a las 9:30 PM.""",
            keyVocab = listOf(
                "wake up" to "despertarse",
                "take a shower" to "tomar una ducha",
                "have breakfast" to "desayunar",
                "go to school" to "ir al colegio",
                "study" to "estudiar",
                "have lunch" to "almorzar",
                "play the violin" to "tocar el violín",
                "do homework" to "hacer la tarea",
                "have dinner" to "cenar",
                "go to bed" to "acostarse"
            ),
            questions = listOf(
                DiagnosticQuestion(
                    id = 304,
                    category = QuestionCategory.VOCABULARY,
                    promptEn = "How does Sofia travel to school?",
                    promptEs = "¿Cómo viaja Sofía al colegio?",
                    optionsEn = listOf("By red double-decker bus", "By bicycle", "Walking", "By subway"),
                    optionsEs = listOf("En autobús rojo de dos pisos", "En bicicleta", "Caminando", "En metro"),
                    correctIndex = 0,
                    explanationEn = "The story states: 'I go to school by red double-decker bus.'",
                    explanationEs = "El relato afirma: 'I go to school by red double-decker bus.'"
                ),
                DiagnosticQuestion(
                    id = 305,
                    category = QuestionCategory.FREQUENCY,
                    promptEn = "How often does Sofia watch television late at night?",
                    promptEs = "¿Con qué frecuencia ve televisión Sofía tarde en la noche?",
                    optionsEn = listOf("Rarely (almost never)", "Always (every day)", "Often", "Usually"),
                    optionsEs = listOf("Rarely (rara vez)", "Always (siempre)", "Often (a menudo)", "Usually (usualmente)"),
                    correctIndex = 0,
                    explanationEn = "Sofia says: 'I rarely watch TV late; I always go to bed at 9:30 PM.'",
                    explanationEs = "Sofía dice: 'I rarely watch TV late; I always go to bed at 9:30 PM.'"
                )
            ),
            imageResName = "reading_sofia"
        ),
        ReadingStoryItem(
            id = "story_kenji",
            titleEn = "Kenji's Tech & Robotics Day in Tokyo",
            titleEs = "El día tecnológico y de robótica de Kenji en Tokio",
            characterName = "Kenji",
            location = "Tokyo, Japan",
            flag = "🇯🇵",
            textEn = """Greetings! I am Kenji from Tokyo. I am in the 7th grade. 
I usually wake up at 6:15 AM. I stretch, drink green tea, and take a quick shower. At 6:45 AM, my family and I have breakfast: we always have rice, miso soup, and grilled salmon. It is delicious!

At 7:20 AM, I go to school by rapid train. The train is always on time! At school, we study mathematics, Japanese, and robotics. My friends and I have lunch in our classroom at 12:20 PM.

After school ends at 3:30 PM, I participate in the Robotics Club. We build friendly robots that solve mazes. When I return home at 5:30 PM, I always do my homework right away. Then, I sometimes help my parents prepare dinner. We have dinner at 7:45 PM. I read comic books and go to bed at 10:00 PM.""",
            textEs = """¡Saludos! Soy Kenji de Tokio. Estoy en 7.° grado.
Usualmente me despierto a las 6:15 AM. Me estiro, tomo té verde y me doy una ducha rápida. A las 6:45 AM, mi familia y yo desayunamos: siempre comemos arroz, sopa de miso y salmón asado. ¡Es delicioso!

A las 7:20 AM, voy al colegio en tren rápido. ¡El tren siempre llega a tiempo! En la escuela, estudiamos matemáticas, japonés y robótica. Mis amigos y yo almorzamos en nuestro salón de clases a las 12:20 PM.

Después de que el colegio termina a las 3:30 PM, participo en el Club de Robótica. Construimos robots amigables que resuelven laberintos. Cuando regreso a casa a las 5:30 PM, siempre hago mi tarea de inmediato. Luego, a veces ayudo a mis padres a preparar la cena. Cenamos a las 7:45 PM. Leo cómics y me voy a la cama a las 10:00 PM.""",
            keyVocab = listOf(
                "wake up" to "despertarse",
                "take a shower" to "tomar una ducha",
                "have breakfast" to "desayunar",
                "go to school" to "ir al colegio",
                "study robotics" to "estudiar robótica",
                "have lunch" to "almorzar",
                "do homework" to "hacer la tarea",
                "help prepare dinner" to "ayudar a preparar la cena",
                "have dinner" to "cenar",
                "go to bed" to "acostarse"
            ),
            questions = listOf(
                DiagnosticQuestion(
                    id = 306,
                    category = QuestionCategory.VOCABULARY,
                    promptEn = "What special club does Kenji attend after school?",
                    promptEs = "¿A qué club especial asiste Kenji después de clases?",
                    optionsEn = listOf("Robotics Club", "Cooking Club", "Soccer Club", "Drama Club"),
                    optionsEs = listOf("Club de Robótica", "Club de Cocina", "Club de Fútbol", "Club de Teatro"),
                    correctIndex = 0,
                    explanationEn = "Kenji says: 'I participate in the Robotics Club. We build friendly robots.'",
                    explanationEs = "Kenji menciona: 'I participate in the Robotics Club. We build friendly robots.'"
                ),
                DiagnosticQuestion(
                    id = 307,
                    category = QuestionCategory.FREQUENCY,
                    promptEn = "According to Kenji, how often does he help his parents prepare dinner?",
                    promptEs = "Según Kenji, ¿con qué frecuencia ayuda a sus padres a preparar la cena?",
                    optionsEn = listOf("Sometimes (50%)", "Always (100%)", "Never (0%)", "Rarely (20%)"),
                    optionsEs = listOf("Sometimes (a veces)", "Always (siempre)", "Never (nunca)", "Rarely (rara vez)"),
                    correctIndex = 0,
                    explanationEn = "Kenji notes: 'Then, I sometimes help my parents prepare dinner.'",
                    explanationEs = "Kenji afirma: 'Then, I sometimes help my parents prepare dinner.'"
                )
            ),
            imageResName = "reading_kenji"
        )
    )

    // MISSION FINAL: 5 Rubric Criteria (Max 20 Points)
    val rubricCriteria: List<RubricItem> = listOf(
        RubricItem(
            id = "vocab",
            titleEn = "1. Vocabulary of Daily Routines",
            titleEs = "1. Vocabulario de rutinas diarias",
            level4En = "Uses a wide variety of routine expressions (wake up, take a shower, etc.) accurately and appropriately.",
            level4Es = "Usa variedad de vocabulario de rutinas con precisión y naturalidad.",
            level3En = "Uses most of the key routine vocabulary adequately with minor inaccuracies.",
            level3Es = "Usa la mayoría del vocabulario adecuadamente con pocos errores.",
            level2En = "Uses limited routine vocabulary or presents noticeable word choice errors.",
            level2Es = "Usa vocabulario limitado o presenta varios errores de selección de palabras.",
            level1En = "Has frequent difficulty communicating basic daily routine actions.",
            level1Es = "Presenta dificultades frecuentes para comunicar acciones cotidianas básicas."
        ),
        RubricItem(
            id = "grammar",
            titleEn = "2. Present Simple Structure",
            titleEs = "2. Estructura del presente simple",
            level4En = "Constructs sentences correctly and accurately applies 3rd person singular (-s, -es, -ies).",
            level4Es = "Construye oraciones correctamente y aplica la tercera persona singular con precisión.",
            level3En = "Presents few structural errors that do not impede overall message understanding.",
            level3Es = "Presenta pocos errores que no impiden comprender el mensaje.",
            level2En = "Presents several grammar errors that partially affect message comprehension.",
            level2Es = "Presenta errores gramaticales que afectan parcialmente el mensaje.",
            level1En = "Requires constant guidance to form basic present simple sentences.",
            level1Es = "Requiere acompañamiento constante para formular oraciones básicas."
        ),
        RubricItem(
            id = "frequency",
            titleEn = "3. Adverbs of Frequency",
            titleEs = "3. Adverbios de frecuencia",
            level4En = "Uses 4 or more frequency adverbs (always, usually, etc.) correctly positioned with clear meaning.",
            level4Es = "Usa 4 o más adverbios correctamente ubicados y con sentido claro.",
            level3En = "Uses various frequency adverbs with few positioning errors.",
            level3Es = "Usa varios adverbios con pocos errores de ubicación.",
            level2En = "Presents recurring errors in adverb positioning or confusing meaning.",
            level2Es = "Presenta errores de posición o significado confuso.",
            level1En = "Does not use adverbs of frequency adequately or omits them.",
            level1Es = "No logra emplear adverbios de frecuencia adecuadamente o los omite."
        ),
        RubricItem(
            id = "communication",
            titleEn = "4. Understandable Communication",
            titleEs = "4. Comunicación comprensible y fluidez",
            level4En = "Clear, well-organized, and easily understandable message with confident delivery.",
            level4Es = "Mensaje claro, comprensible y organizado, transmitido con seguridad.",
            level3En = "Message is generally understandable and organized.",
            level3Es = "Mensaje generalmente comprensible y ordenado.",
            level2En = "Some parts require clarification; pauses or hesitations hinder flow.",
            level2Es = "Algunas partes requieren aclaración; pausas interfieren con la fluidez.",
            level1En = "Communication presents significant comprehension barriers.",
            level1Es = "La comunicación presenta dificultades importantes de comprensión."
        ),
        RubricItem(
            id = "collaboration",
            titleEn = "5. Collaborative Work & Role",
            titleEs = "5. Trabajo colaborativo y cumplimiento de rol",
            level4En = "Actively participates, supports teammates, and fulfills assigned role (Reader/Solver/Checker).",
            level4Es = "Participa, apoya y cumple su rol (Lector/Solucionador/Verificador) activamente.",
            level3En = "Participates and fulfills most group responsibilities cooperatively.",
            level3Es = "Participa y cumple la mayoría de responsabilidades cooperativamente.",
            level2En = "Participates irregularly; requires reminders to fulfill team role.",
            level2Es = "Participa de manera irregular; requiere recordatorios de rol.",
            level1En = "Limited or non-existent participation in collaborative dynamics.",
            level1Es = "Su participación en la dinámica colaborativa es muy limitada."
        )
    )

    // Badges
    val badges: List<BadgeItem> = listOf(
        BadgeItem(
            id = "badge_vocab",
            titleEn = "Vocabulary Explorer",
            titleEs = "Explorador de Vocabulario",
            descriptionEn = "Mastered the 10 core daily routine flashcards & associations.",
            descriptionEs = "Dominó las 10 tarjetas y asociaciones de rutinas diarias.",
            iconEmoji = "🌟"
        ),
        BadgeItem(
            id = "badge_grammar",
            titleEn = "Grammar Hero",
            titleEs = "Héroe Gramatical",
            descriptionEn = "Conquered Present Simple 3rd person singular and negatives.",
            descriptionEs = "Conquistó la 3ª persona del presente simple y las negaciones.",
            iconEmoji = "⚡"
        ),
        BadgeItem(
            id = "badge_reading",
            titleEn = "Reading Detective",
            titleEs = "Detective de Lectura",
            descriptionEn = "Completed teenager routine reading missions across the globe.",
            descriptionEs = "Completó las misiones de lectura de rutinas de adolescentes.",
            iconEmoji = "🔍"
        ),
        BadgeItem(
            id = "badge_writing",
            titleEn = "Routine Author",
            titleEs = "Autor de Rutinas",
            descriptionEn = "Published a 5-7 sentence personal daily routine on the wall.",
            descriptionEs = "Publicó su rutina personal de 5-7 oraciones en el muro.",
            iconEmoji = "✍️"
        ),
        BadgeItem(
            id = "badge_communication",
            titleEn = "Communication Star",
            titleEs = "Estrella de la Comunicación",
            descriptionEn = "Prepared and presented oral routine presentation with rubric.",
            descriptionEs = "Preparó y presentó la rutina oral con autoevaluación.",
            iconEmoji = "🎤"
        ),
        BadgeItem(
            id = "badge_mission_master",
            titleEn = "Mission Master",
            titleEs = "Maestro de la Aventura",
            descriptionEn = "Completed all 7 learning missions in English Adventure!",
            descriptionEs = "¡Completó las 7 misiones de aprendizaje en English Adventure!",
            iconEmoji = "🚀"
        )
    )
}
