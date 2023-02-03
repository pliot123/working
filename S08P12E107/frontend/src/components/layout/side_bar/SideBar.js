import BadgeModal from "./BadgeModal";
import { useContext } from "react";
import AuthContext from "../../../store/auth-context";
import CreateRoomModal from "./CreateRoomModal";
import FriendListModal from "./FriendListModal";
import Button from "../../UI/Button";
import "./SideBar.module.css";
import { Link, useNavigate } from "react-router-dom";

// 사이드바 만들기. 이후 수정
// 사이드바는 로비, 게시판에 있을 경우 (유저프로필, 방 생성, 친구 목록, 랭킹) 이 표시되고,
//            마이페이지에 있을 경우 (유저 프로필 + 경험치 바, 뱃지 목록, 친구 목록, 회원정보 수정, 회원 탈퇴)가 표시된다.
const SideBar = (props) => {
  const navigate = useNavigate();

  const isMyPage = props.isMyPage;
  const authCtx = useContext(AuthContext);

  return (
    <aside>
      {/* 유저 프로필 */}
      <div>
        {/* <p>{authCtx.gender}</p> */}
        <Link to="/mypage">{authCtx.nickname}</Link>
      </div>

      {isMyPage ? (
        <div>
          {/* 뱃지 모달 */}
          <BadgeModal />

          {/* 친구 목록 모달 */}
          <FriendListModal />

          {/* 회원정보 페이지로 이동 */}
          <Button onClick={() => navigate("/update")}>회원정보 수정</Button>

          {/* 회원 탈퇴 페이지로 이동 */}
          <Button onClick={() => navigate("/delete")}>회원 탈퇴</Button>
        </div>
      ) : (
        <div>
          {/* 방 생성 모달 */}
          <CreateRoomModal />

          {/* 친구 목록 모달 */}
          <FriendListModal />

          {/* 실시간 랭킹 */}
          <div>
            <p>실시간 랭킹</p>
          </div>
        </div>
      )}
    </aside>
  );
};

export default SideBar;
