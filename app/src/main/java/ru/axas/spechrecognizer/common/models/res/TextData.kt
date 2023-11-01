package ru.axas.spechrecognizer.common.models.res

object TextApp {

    fun initDebugAndVersion(isDebugInit: Boolean, versionNameInit: String) {
        isDebug = isDebugInit
        versionName = versionNameInit
    }

    @Volatile var isDebug = false
    @Volatile var versionName: String = ""

    val versionApp: () -> String = { "Версия $versionName" }

    val mockEmail: () -> String = { if (isDebug) "test@mum.ru" else "" }
    val mockPass: () -> String = { if (isDebug) "1234567Q" else "" }

//    val mockEmail: () -> String = { if (isDebug) "den@example.com" else "" }
//    val mockPass: () -> String = { if (isDebug) "Qwerty123" else "" }

    const val baseTextNameApp: String = "Voice Recognition"
    const val FOLDER_NAME: String = "FAMILY_VIBE"
    const val linkAxas: String = "https://axas.ru/"
    const val linkPrivatePolicy: String = "http://support.axas.ru/family_vibe_policy.html"
    const val TELEGRAM_AXAS_BOT: String = "AXAS_BOT"
    const val TELEGRAM_IAXAS: String = "iAXAS"
    const val divForeRub: Int = 100
    val FORMAT_TELEGRAM_URL: (Any?) -> String = { "https://t.me/$it" }
    val FORMAT_TELEGRAM_INTENT: (Any?) -> String = { "tg://resolve?domain=$it" }
    val FORMAT_WHATSAPP_URL: (Any?) -> String = { "https://api.whatsapp.com/send?phone=$it" }
    val FORMAT_WHATSAPP_INTENT: (Any?) -> String = { "whatsapp://send/?phone=$it" }
    val FORMAT_TEL_INTENT: (Any?) -> String = { "tel:+$it" }
    val FORMAT_LINK_MARKET_HTTPS: (Any?) -> String = { "https://play.google.com/store/apps/details?id=$it" }
    val FORMAT_LINK_MARKET: (Any?) -> String = { "market://details?id=$it" }
    const val TEXT_OS: String = "android"

    const val symbolPlus: String = "+"
    const val symbolMinus: String = "-"
    const val symbolDollar: String = "$"
    const val symbolImportant: String = "  ⃰"
    const val symbolAsterisk: String = "⁂"
    const val symbolStartDescription: String = "*-"

    const val cancel: String = "Отмена"
    const val ok: String = "Ок"
    const val next: String = "Продолжить"
    const val add: String = "Добавить"
    const val fillInLater: String = "Заполнить позже"
    const val exit: String = "Выход"
    const val exitProfile: String = "Выход из профиля"
    const val reallyWantToExitProfile: String = "Вы действительно хотите выйти из своего профиля?"
    const val postType: String = "Тип постов"
    const val change: String = "Изменить"
    const val aboutTheDeveloper: String = "О разработчике"
    const val ribbon: String = "Лента"
    const val affairs: String = "Дела"
    const val allPosts: String = "Все посты"
    const val stories: String = "Истории"
    const val skip: String = "Пропустить"
    const val chats: String = "Чаты"
    const val from: String = "от"
    const val before: String = "до"
    const val calendar: String = "Календарь"
    const val photo: String = "Фото"
    const val video: String = "Видео"
    const val polls: String = "Опросы"
    const val gifts: String = "Подарки"
    const val chooseFamily: String = "Выбрать семью"
    const val editProfile: String = "Редактировать профиль"
    const val personalData: String = "Личные данные"
    const val media: String = "Медиа"
    const val albumNotSelected: String = "Альбом не выбран"
    const val newAlbum: String = "Новый альбом"
    const val wishList: String = "Вишлист"
    const val awards: String = "Награды"
    const val create: String = "Создать"
    const val subject: String = "Тематика"
    const val visibleToAll: String = "Видно всем"
    const val familyOnly: String = "Только семье"
    const val now: String = "Сейчас"
    const val noSubject: String = "Без темы"
    const val allRibbon: String = "Вся лента"
    const val familyRibbon: String = "Семья"
    const val chat: String = "Чат"
    const val allFamilies: String = "Все семьи"
    const val incoming: String = "Входящие"
    const val outgoing: String = "Исходящие"
    const val address: String = "Адрес"
    const val mailNotRegistered: String = "Данная электронная почта не зарегистрирована в системе, для входа пройдите регистрацию"
    const val myMedia: String = "Мои медиа"
    const val all: String = "Все"
    const val applications: String = "Заявки"
    const val invitations: String = "Приглашения"
    const val search: String = "Поиск"
    const val favorites: String = "Избранное"
    const val phoneMask: String = "70000000000"
    const val name: String = "Имя"
    const val surname: String = "Фамилия"
    const val phone: String = "Телефон"
    const val time: String = "Время"
    const val date: String = "Дата"
    const val family: String = "Семья"
    const val contacts: String = "Контакты"
    const val patronymic: String = "Отчество"
    const val save: String = "Сохранить"
    const val leaveFeedback: String = "Оставить отзыв"
    const val more: String = "Подробнее"
    const val showAll: String = "Показать всех"
    const val showAllS: String = "Показать все"
    const val viewAll: String = "Посмотреть всё"
    const val contactDeveloper: String = "Связаться с разработчиком"
    const val exitApp: String = "Выйти из приложения?"
    const val reallyWantToDeleteProfile: String = "Вы действительно хотите удалить безвозвратно свой профиль?"

    const val applicationDevelopedDigital: String = "Приложение разработано\nв Digital студии"
    const val missingPermission: String = "Отсутствуют необходимые разрешения"
    const val stageConfirmed: String = "В работе"
    const val on: String = "включены"
    const val inHourFeed: String = "В своей ленте"
    const val inTheMessage: String = "В сообщении"
    const val addALink: String = "Добавьте ссылку"
    const val link: String = "Cсылкa"
    const val didNotChooseAFamily: String = "Вы не выбрали семью или что-то пошло не так!"
    const val telegramNotInstalled: String = "Telegram не установлен!"
    const val whatsappNotInstalled: String = "Whatsapp не установлен!"
    const val somethingWentWrong: String = "Что-то пошло не так"
    const val wishlistSecret: String = "Сделать вишлист секретным"
    const val descriptionWishlistSecret: String = "Видеть его сможете только вы и соавторы."
    const val itEmpty: String = "Тут пусто"
    const val comment: String = "Комментарий"
    const val answerComment: String = "Ответ на комментарий"
    const val myFamily: String = "Моя семья"
    const val roleInTheFamily: String = "Роль в семье"
    const val acceptApplication: String = "Принять заявку"
    const val addToFamily: String = "Добавить в семью"
    const val writeAnAccompanying: String = "Пожалуйста, напишите сопроводительное письмо, чтобы пользователь положительно ответил на ваш запрос."
    const val reject: String = "Отклонить"
    const val coveringLetter: String = "Сопроводительное письмо"
    const val sort: String = "Сортировать"
    const val itNoPhotosYet: String = "В этом вишлисте \nпока нет желаний"
    const val createAFamilyCell: String = "Создать семейную ячейку"
    const val joinAFamilyUnit: String = "Присоединиться к семейной ячейке"
    const val enterTheDetailsOfYourFamilyMembers: String =
        "Введите данные членов вашей семьи и отправьте им приглашение.*"
    const val creatingANewFamilyUnitInTheApplication: String =
        "Создание новой семейной ячейки в приложении."
    const val signInWithAnInvite: String =
        "Войдите с помощью приглашения или ID семейной ячейки."
    const val welcome: String = "Добро пожаловать!"
    const val createAnAccount: String = "Создание учётной записи"
    const val enterYourEmail: String = "Введите почту"
    const val confirmationCode: String = "Код подтверждения"
    const val enterConfirmationCode: String = "Введите код подтверждения"
    const val tellUsAboutYourInterests: String = "Расскажите о ваших интересах"
    const val createACell: String = "Создание ячейки"
    const val enterFamilyCellID: String = "Введите ID семейной ячейки"
    const val familyCellID: String = "ID семейной ячейки"
    const val welcomeFamilyVibe: String = "Добро пожаловать\nв Family Vibe!"
    const val enterYourDetails: String = "Введите ваши данные."
    const val enterYourEmailDescription: String = "Введите электронную почту, которую указывали при регистрации"
    const val yourFamilyUnit: String = "Ваша семейная ячейка"
    const val welcomeInFamilyVibe: String = "Добро пожаловать в Family Vibe!"
    const val registerWithEmail: String = "Зарегистрируйтесь с помощью электронной почты."
    const val emailAddress: String = "Адрес электронной почты"
    const val password: String = "Пароль"
    const val passwordNoSimilar: String = "Пароли не совпадают"
    const val passwordNoRegular: String = "Пароль должен содержать минимум:" +
            "\n8 символов, одну латинскую букву, одну цифру"
    const val emailNoValide: String = "Неверный формат электронной почты"
    const val passwordTwo: String = "Повторите пароль"
    const val photoFull: String = "Фотография"
    const val namePr: String = "Имя*"
    const val noName: String = "Без названия"
    const val nameTitle: String = "Название"
    const val complain: String = "Пожаловаться"
    const val blockUser: String = "Заблокировать пользователя"
    const val userExist: String = "Такого пользователя еще не существует"
    const val stateReasonComplain: String = "Укажите причину жалобы"
    const val complaint: String = "Жалоба"
    const val deletePhotos: String = "Удалить выбранные фото"
    const val deleteSelectedPhotos: String = "Удалить выбранные фото, без возможности восстановления.?"
    const val invite: String = "Запрос на дружбу"
    const val chooseARoleInTheFamily: String = "Выберите роль в семье"
    const val deletingPost: String = "Удаление поста"
    const val reallyWantToRemoveDesire: String = "Вы действительно хотите удалить желание?"
    const val deleteWishes: String = "Удаление желания."
    const val deleteWishesList: String = "Удаление листа желаний."
    const val reallyWantToRemoveWishesList: String = "Вы действительно хотите удалить лист желаний?"
    const val description: String = "Описание"
    const val wish: String = "Желание"
    const val wishUpdate: String = "Желание обновлено"
    const val listWish: String = "Лист Желаний"
    const val book: String = "Забронировать"
    const val booked: String = "Забронировано"
    const val gave: String = "Подарили"
    const val addSomething: String = "Добавить что-нибудь"
    const val addressOrPlace: String = "Адрес или место"
    const val viewNextText: String = "развернуть"
    const val goneText: String = "скрыть"
    const val notUnreadNotifications: String = "У вас нет\nнепрочитанных уведомлений"
    const val creatingAFamilyDescription: String =
        "при создании семейной ячейки вводятся данные родителей и детей. Только родители (муж и жена) могут создать семейную ячейку."
    const val biography: String = "Биография"
    const val interests: String = "Интересы"
    const val birthDayWye: String = "Дата рождения*"
    const val customDay: String = "Дата*"
    const val birthDay: String = "Дата рождения"
    const val gender: String = "Пол*"
    const val gender_: String = "Пол"
    const val surname_: String = "Фамилия*"
    const val gift: String = "Подарки"
    const val newWishlist: String = "Новый вишлист"
    const val albumCreated: String = "Альбом создан"
    const val cityOfBirth: String = "Город рождения"
    const val mobilePhone: String = "Мобильный телефон"
    const val notSpecified: String = "Не указан"
    const val telegram: String = "Телеграм"
    const val addInterests: String = "Добавьте интересы"
    const val enterARequest: String = "Введите запрос"
    const val cityOfResidence: String = "Город проживания"
    const val specifyGender: String = "Укажите пол"
    const val detailedInformation: String = "Подробная информация"
    const val searchFilter: String = "Фильтр поиска"
    const val birthday: String = "День рождения"
    const val city: String = "Город"
    const val originalCity: String = "Родной город"
    const val mobilPhone: String = "Мобильный телефон"
    const val phoneUpdate: String = "Номер телефона обновлен"
    const val tg: String = "Телеграм"
    const val aboutYourself: String = "О себе"
    const val aboutApp: String = "О приложении"
    const val doing: String = "Деятельность"
    const val dreateAPost: String = "Создать пост"
    const val likeMusics: String = "Любимая музыка"
    const val likeFilms: String = "Любимые фильмы"
    const val likeBooks: String = "Любимые книги"
    const val likeGames: String = "Любимые игры"
    const val maidenName: String = "Девичья фамилия"
    const val obligatoryField: String = "Обязательное поле"
    const val iAmSuchAndSuch: String = "Я такой-то такой-то "
    const val openTheCamera: String = "Открыть камеру"
    const val openGallery: String = "Открыть галерею"
    const val mailConfirmationCode: String = "Код подтверждения почты"
    const val phoneConfirmationCode: String = "Введите последние 4-е цифры из номера входящего звонка"
    const val registered: String = "Регистрация"
    const val forgotPassword: String = "Забыли пароль?"
    const val toComeIn: String = "Войти"
    const val dataUpdated: String = "Данные обновлены."
    const val privateAlbum: String = "Приватный альбом"
    const val privacyPolicy: String = "Политикой конфиденциальности"
    const val privacyPolicyR: String = "Политика конфиденциальности"
    const val forAMorePreciseSearch: String =
        "*-обязательные поля для более точного поиска совпадений с родственниками"
    const val changeEmailAddress: String = "Изменить адрес почты"
    const val linkAgreement: String = "согласие"
    const val agreementWhenInterPhone: String =
        "Даю $linkAgreement на обработку персональных данных"
    const val linkConditionsWhenInterPhone: String = "политики конфиденциальности"
    const val conditionsWhenInterPhone: String =
        "Принимаю условия $linkConditionsWhenInterPhone"
    const val agreementPersonal: String =
        "Даю $linkAgreement на обработку персональных данных"
    const val agreementNews: String = "Даю $linkAgreement на получение новостей"
    const val genderOther: String = "Другое"
    const val any: String = "Любой"
    const val iPreferNotToAnswer: String = "Предпочитаю не отвечать"
    const val genderMan: String = "Мужской"
    const val genderWoman: String = "Женский"
    const val genderManShort: String = "Муж"
    const val genderWomanShort: String = "Жена"
    const val children: String = "Дети"
    const val incorrectCode: String = "Неверный код"
    const val photos: String = "Фотографии"
    const val album: String = "Альбом"
    const val uploadAPhoto: String = "Загрузить фото"
    const val grandParent: String = "Родители"
    const val brotherSister: String = "Брат/Сестра"
    const val addSpouse: String = "Добавить супруга"
    const val addChild: String = "Добавить детей"
    const val addSelf: String = "Добавить себя"
    const val addParent: String = "Добавить родителей"
    const val addSibling: String = "Добавить брата или сестру"
    const val wrongLoginOrPassword: String = "Неверный логин или пароль"
    const val spouse: String = "Супруг"
    const val addedToFavorites: String = "Добавлено в избранное"
    const val deletedToFavorites: String = "Удалено из избранного"
    const val child: String = "Дети"
    const val self: String = "Я"
    const val parent: String = "Родители"
    const val sibling: String = "Брат/Сестра"
    const val allFiles: String = "Все файлы"
    const val wishlist: String = "Вишлисты"
    const val albums: String = "Альбомы"
    const val allWishes: String = "Все желания"
    const val chooseAlbum: String = "Выберите альбом"
    const val answer: String = "Ответить"
    const val post: String = "Пост"
    const val showAllAnswers: String = "Посмотреть все ответы"
    const val spam = "Спам"
    const val age = "Возраст"
    const val photosUploaded = "Фото загружены"
    const val photoUploaded = "Фото загружено"
    const val errorAddingToAlbum = "Ошибка добавления медиа в альбом"
    const val nudityOrSexual = "Изображения обнаженного тела или действий сексуального характера"
    const val hostileSayings = "Враждебные высказывания или символы Враждебные высказывания или символы"
    const val violenceOrDangerous = "Насилие или опасные организации"
    const val bullyingOrPersecution = "Травля или преследования"
    const val saleIllegal = "Продажа незаконных товаров или товаров, подлежащих правовому регулированию"
    const val violationIntellectual = "Нарушение прав на интеллектуальную собственность"
    const val suicide = "Самоубийство или нанесение себе увечий"
    const val userComplaint = "Жалоба на пользователя"
    const val fraudOrDeception = "Мошенничество или обман"
    const val fakeInformation = "Ложная информация"
    const val addToFavorite: String = "Добавить в избранное"
    const val remake: String = "Редактировать"
    const val toComplain: String = "Пожаловаться"
    const val saveToMedia: String = "Сохранить в медиа"
    const val mediaSaved: String = "Медиа сохранено"
    const val noImagesFound: String = "Изображения не обнаружены"
    const val goToAlbum: String = "Перейти к альбому"
    const val titleEditPhoto: String = "Редактировать фото"
    const val titleDeletePhoto: String = "Удалить фото?"
    const val deleteAlbum: String = "Удаление альбома"
    const val deleteAlbumWithPhoto: String = "Вы действительно хотите удалить альбом вместе с фото?"
    const val rename: String = "Переименовать"
    const val share: String = "Поделиться"
    const val done: String = "Выполнено"
    const val download: String = "Скачать"
    const val downloadAlbum: String = "Скачать альбом"
    const val send: String = "Отправить"
    const val delete: String = "Удалить"
    const val deleteProfile: String = "Удалить профиль"
    const val write: String = "Написать"
    const val requestHasBeenSent: String = "Запрос отправлен"
    const val acceptAsFriend: String = "Принять в друзья"
    const val beFriendsWithFamilies: String = "Дружить семьями"
    const val renameAlbum: String = "Переименовать альбом"
    const val owner: String = "Владелец"
    const val changePicAlbum: String = "Изменить обложку"
    const val rub: String = "₽"
    const val priceRub: String = "Цена, ₽"
    const val price: String = "Цена"
    const val thisIsAClosedAccount: String = "Это закрытый аккаунт"
    const val makeFriendsWithFamily: String = "Подружитесь с семьёй или добавьте в свою семью, чтобы иметь доступ к информации пользователя."
    const val addPhoto: String = "Добавить фотографии"
    const val addOnePhoto: String = "Добавить фото"
    const val addDesire: String = "Добавить желание"
    const val newDesire: String = "Новое желание"
    const val newPost: String = "Новый пост"
    const val postEditing: String = "Редактирование поста"
    const val postPost: String = "Запостить"
    const val block: String = "Заблокировать"
    const val updatePost: String = "Обновить"
    const val anythingNew: String = "Что у вас нового?"
    const val changeDesire: String = "Изменить желание"
    const val myAlbom: String = "Мой альбом"
    const val published: String = "Опубликовано"
    const val noPhotoInAlbum: String = "В этом альбоме\nпока нет фотографий"
    const val genderInterWoman: String = "Введите данные вашей жены."
    const val genderInterMan: String = "Введите данные вашего мужа."
    const val genderInterSatellite: String = "Введите данные вашего спутника."
    const val enterChildren: String = "Введите данные выших детей."
    const val checkYourFamilyDetails: String = "Проверьте данные вашей семьи"

    const val errorSomethingWrong: String = "Что-то пошло не так, попробуйте повторить позже"
    const val errorPagingContent: String = "Ошибка загрузки контента"
    const val errorEnterADeliveryPhoneNumber: String = "Укажите номер телефона"
    const val errorCreateAlbum: String = "Ошибка при создании альбома"
    const val errorInvalidCodeEntered: String = "Введен неверный код"

    val formatNotRegisteredYet: (Any?) -> String = { "Ещё не зарегистрированы?   $it" }
    val formatYouAgreeTo: (Any?) -> String = { "Продолжая, вы соглашаетесь с $it" }
    val formatUserComplaint: (Any?) -> String = {
        it.toString().ifEmpty { null }?.let{ value ->"Жалоба на $value"}   ?: "Жалоба"}
    val formatAlreadyHaveAnAccount: (Any?) -> String = { "Уже есть учётная запись?   $it" }
    val formatConfirmationCodeSentYourEmail: (Any?) -> String =
        { "На Ваш адрес электронной почты $it выслан код подтверждения. Введите код или перейдитепо ссылке из письма." }
    val formatDollar: (Any?) -> String = { " $it $" }
    val formatDownloadAlbum: (baseUrl: String, Any?) -> String = { baseUrl, str2 -> "$baseUrl/api/albums/$str2/download/" }
    val formatStepFrom: (Any?, Any?) -> String = { str1, str2 -> "Шаг $str1 из $str2" }
    val formatRub: (Any?) -> String = { "$it ₽" }
    val formatQty: (Any?) -> String = { " $it шт." }
    val formatReallyWantDeletingPost: (Any?) -> String = { "Вы действительно хотите удалить \"${it}...\"?" }
    val formatDeletingUserInFamily: (Any?) -> String = { "Удалить ${it ?: ""} из семьи?" }
    val formatBlockUser: (Any?) -> String = { "Вы действительно хотите заблокировать ${it ?: ""}?" }
    val formatWeight: (Any?) -> String = { "$it г" }
    val formatShowNews: (Any?, Boolean) -> String = { str, bool ->
        if (!bool) "Показывать новости $str"
        else "Не показывать новости $str"
    }
    val formatOrderNumber: (Any?) -> String = { "Ордер №$it" }
    val formatTextPhoneSend: (Any?) -> String = { "Будет произведен звонок на номер\n$it" }
    val formatPostedCreated: (Any?) -> String = { "Размещен $it" }
    val formatHelloUser: (Any?) -> String = { "Привет, $it" }
    val formatProductsFromSupplier: (Any?) -> String = { "Продукты от ${it ?: "..."}" }
    val formatPushNotificationsOnOff: (Any?) -> String = { "Push-уведомления ${it ?: ""}" }
    val formatSentARequestToJoin: (Any?, Any?) -> String =
        { str1, str2 -> "Вы отправили запрос на присоединение к семейной ячейки ${str1 ?: ""} ${str2 ?: ""}. Ожидайте, пока глава ячейки не примет ваше приглашение. " }
    val formatDeadlineDay: (Any?) -> String = { "$it дней" }
    val formatDataCreated: (Any?) -> String = { "Дата оформления: ${it ?: ""}" }
    val formatDeliveryTime: (Any?) -> String = { "Доставка к: ${it ?: ""}" }
    val formatChooseARoleInTheFamily: (Any?) -> String = { "Выберите роль в семье ${it?.let { str -> "для $str" } ?: ""}" }

    val formatGetCodeAgain: (Any?) -> String = { "Отправить код повторно через $it" }
    val formatDelete: (Any?) -> String = {
        "Фотография “$it”" + "будет удалена без возможности восстановления."
    }
    val formatSomethingYou: (Any?) -> String = { "$it (Вы)" }
    val formatAuthorFaq: (Any?, Any?) -> String = { str1, str2 -> "Автор: $str1, $str2" }
    val formatNumbOrderAndStatus: (Any?, Any?) -> String =
        { str1, str2 -> "№ ${str1 ?: "-"} ${str2 ?: ""}" }
    val formatUpdateFaq: (Any?) -> String = { str1 -> "Обновлено: $str1" }
    val formatTextSelectedN: (Any?) -> String = { str -> "Выбрано: $str" }

}