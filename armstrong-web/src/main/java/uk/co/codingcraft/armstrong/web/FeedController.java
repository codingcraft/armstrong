package uk.co.codingcraft.armstrong.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uk.co.codingcraft.armstrong.manager.EntryManager;
import uk.co.codingcraft.armstrong.manager.UserManager;
import uk.co.codingcraft.armstrong.model.Entry;
import uk.co.codingcraft.armstrong.model.Feed;
import uk.co.codingcraft.armstrong.model.User;

import java.util.Collection;

@Controller
@RequestMapping("/")
@Transactional
public class FeedController {

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private EntryManager entryManager;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		User user = userManager.getCurrentUser();
		ModelAndView mav = new ModelAndView("feeds");
		Collection<Feed> feeds = user.getSubscriptions();
		Collection<Entry> entries = entryManager.findEntriesForUser(user, new PageRequest(0, 50) );

		mav.addObject("feeds", feeds);
		mav.addObject("entries", entries);

		return mav;
	}
}
