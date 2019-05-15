module Pages.Attendance exposing (routerLink, view)

import Browser
import Html exposing (..)
import Html.Attributes exposing (..)



-- VIEW


view : Browser.Document msg
view =
    { title = "勤務時間の一覧"
    , body =
        [ text "勤務時間の一覧"
        , ul []
            [ routerLink "/dashboard"
            , routerLink "/payroll/2019-05"
            , routerLink "/attendance/1/2019-05"
            , routerLink "/timerecord/1/2019-05-01"
            ]
        ]
    }


routerLink : String -> Html msg
routerLink path =
    li [] [ a [ href path ] [ text path ] ]
